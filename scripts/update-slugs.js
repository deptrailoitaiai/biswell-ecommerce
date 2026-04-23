/**
 * Script cập nhật slug, meta_title, meta_description cho tất cả sản phẩm.
 * Chạy: node scripts/update-slugs.js
 */
const mysql = require('mysql2/promise');

// ── Vietnamese slug generator ────────────────────────────────────────────────
const MAP = [
  [/[àáảãạăắặẳẵằâầấẩẫậ]/g, 'a'],
  [/[ÀÁẢÃẠĂẮẶẲẴẰÂẦẤẨẪẬ]/g, 'a'],
  [/[èéẻẽẹêềếểễệ]/g, 'e'],
  [/[ÈÉẺẼẸÊỀẾỂỄỆ]/g, 'e'],
  [/[ìíỉĩị]/g, 'i'],
  [/[ÌÍỈĨỊ]/g, 'i'],
  [/[òóỏõọôồốổỗộơờớởỡợ]/g, 'o'],
  [/[ÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢ]/g, 'o'],
  [/[ùúủũụưừứửữự]/g, 'u'],
  [/[ÙÚỦŨỤƯỪỨỬỮỰ]/g, 'u'],
  [/[ỳýỷỹỵ]/g, 'y'],
  [/[ỲÝỶỸỴ]/g, 'y'],
  [/[đ]/g, 'd'],
  [/[Đ]/g, 'd'],
];

function toSlug(str) {
  if (!str) return '';
  let s = str;
  for (const [pat, rep] of MAP) s = s.replace(pat, rep);
  return s
    .toLowerCase()
    .replace(/[^a-z0-9\s-]/g, '')
    .trim()
    .replace(/[\s-]+/g, '-');
}

function ensureUnique(slug, existingSlugs) {
  let candidate = slug;
  let i = 1;
  while (existingSlugs.has(candidate)) {
    candidate = `${slug}-${i++}`;
  }
  return candidate;
}

// Tạo meta description chuẩn SEO (140-160 ký tự)
function buildMetaDesc(pname, pdesc, category) {
  const cat = category ? ` ${category}` : '';
  const base = pdesc ? pdesc.trim() : '';

  // Nếu pdesc đủ dài → cắt gọn
  if (base.length >= 140) {
    const cut = base.substring(0, 155);
    const lastSpace = cut.lastIndexOf(' ');
    return (lastSpace > 100 ? cut.substring(0, lastSpace) : cut) + '…';
  }

  // Nếu pdesc quá ngắn → bổ sung template
  const suffix = ` | ${pname} - Biswell${cat}`;
  const combined = base + suffix;
  if (combined.length <= 160) return combined;
  return combined.substring(0, 157) + '…';
}

// ── Main ─────────────────────────────────────────────────────────────────────
(async () => {
  const conn = await mysql.createConnection({
    host: '103.118.28.79',
    port: 3306,
    user: 'root',
    password: 'S0nTay7991@',
    database: 'biswell',
    charset: 'utf8mb4',
  });

  console.log('✅ Kết nối DB thành công');

  // 1. Đảm bảo cột tồn tại
  await conn.execute(`
    ALTER TABLE product
      ADD COLUMN IF NOT EXISTS slug VARCHAR(300) NULL,
      ADD COLUMN IF NOT EXISTS meta_title VARCHAR(120) NULL,
      ADD COLUMN IF NOT EXISTS meta_description VARCHAR(300) NULL
  `).catch(() => {}); // ignore nếu đã tồn tại riêng lẻ

  // Thêm từng cột riêng nếu ALTER tổng không hỗ trợ
  for (const col of [
    "ADD COLUMN IF NOT EXISTS slug VARCHAR(300) NULL",
    "ADD COLUMN IF NOT EXISTS meta_title VARCHAR(120) NULL",
    "ADD COLUMN IF NOT EXISTS meta_description VARCHAR(300) NULL",
  ]) {
    await conn.execute(`ALTER TABLE product ${col}`).catch(() => {});
  }

  // 2. Lấy toàn bộ sản phẩm
  const [products] = await conn.execute(`
    SELECT p.id, p.pname, p.pdesc, p.slug, c.category_name
    FROM product p
    LEFT JOIN categories c ON p.category_id = c.category_id
  `);

  console.log(`📦 Tìm thấy ${products.length} sản phẩm`);

  // 3. Tập hợp slug đã có (để đảm bảo unique)
  const usedSlugs = new Set();
  // Trước hết gom slug cũ đã có
  for (const p of products) {
    if (p.slug) usedSlugs.add(p.slug);
  }

  // 4. Cập nhật từng sản phẩm
  let updated = 0;
  let skipped = 0;

  for (const p of products) {
    const pname = p.pname || '';
    const pdesc = p.pdesc || '';
    const catName = p.category_name || '';

    // Slug: tái sinh nếu chưa có
    let slug = p.slug;
    if (!slug) {
      const base = toSlug(pname);
      // Bỏ slug cũ khỏi pool trước khi tìm unique
      slug = ensureUnique(base, usedSlugs);
      usedSlugs.add(slug);
    }

    // Meta title: "Tên SP - Biswell | Danh mục"
    const metaTitleRaw = catName
      ? `${pname} - Biswell | ${catName}`
      : `${pname} - Biswell`;
    const metaTitle = metaTitleRaw.length > 120
      ? metaTitleRaw.substring(0, 117) + '…'
      : metaTitleRaw;

    // Meta description
    const metaDesc = buildMetaDesc(pname, pdesc, catName);

    await conn.execute(
      `UPDATE product SET slug=?, meta_title=?, meta_description=? WHERE id=?`,
      [slug, metaTitle, metaDesc, p.id]
    );

    console.log(`  [${p.id}] "${pname}" → /san-pham/${slug}`);
    updated++;
  }

  // 5. Unique index (idempotent)
  await conn.execute(
    `ALTER TABLE product ADD UNIQUE INDEX uq_product_slug (slug)`
  ).catch(e => {
    if (e.code !== 'ER_DUP_KEYNAME') console.warn('  Index:', e.message);
  });

  await conn.end();
  console.log(`\n✅ Xong: ${updated} sản phẩm đã cập nhật, ${skipped} bỏ qua.`);
})().catch(err => {
  console.error('❌ Lỗi:', err.message);
  process.exit(1);
});

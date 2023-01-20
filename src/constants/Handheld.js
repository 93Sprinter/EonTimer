const handheld = {
  GBA: "GBA",
  "NDS-GBA": "NDS-GBA",
  NDS: "NDS",
  "3DS": "3DS",
};

const fps = {
  GBA: 59.7275005696058,
  "NDS-GBA": 59.6555,
  NDS: 59.82609828808082,
  "3DS": 59.83122493939037,
};

const getFrameRate = (handheld) => {
  return 1000 / fps[handheld];
};

module.exports = {
  handheld,
  getFrameRate,
};

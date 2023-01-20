const path = "./sounds/";

const sound = {
  BEEP: "BEEP",
  TICK: "TICK",
  DING: "DING",
  POP: "POP",
  SILENT: "SILENT",
};

const soundFile = {
  BEEP: "beep.wav",
  TICK: "tick.wav",
  DING: "ding.wav",
  POP: "pop.wav",
  SILENT: "silence.wav",
};

const getSound = (sound) => {
  const audio = new Audio(path + soundFile[sound]);
  audio.load();
  return audio;
};

module.exports = {
  sound,
  getSound,
};

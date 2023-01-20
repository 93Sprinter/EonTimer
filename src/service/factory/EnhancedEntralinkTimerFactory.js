import entralinkTimerFactory from "./EntralinkTimerFactory";

const ENTRALINK_FRAME_RATE = 0.837148929;

const createStages = (targetSecond = 0, targetDelay = 0, targetAdvances = 0, calibration = 0, entralinkCalibration = 0, frameCalibration = 0, handheld) => {
  const stages = entralinkTimerFactory.createStages(targetSecond, targetDelay, calibration, entralinkCalibration, handheld);
  return [stages[0], stages[1], stage3(targetAdvances, frameCalibration)];
};

const calibrate = (targetAdvances = 0, actualAdvances = 0) => {
  return Math.round((targetAdvances - actualAdvances) / ENTRALINK_FRAME_RATE) * 1000;
};

const stage3 = (targetAdvances, frameCalibration) => {
  return Math.round(targetAdvances / ENTRALINK_FRAME_RATE) * 1000 + frameCalibration;
};

export default {
  createStages,
  calibrate,
};

import delayTimerFactory from "./DelayTimerFactory";

const createStages = (targetSecond = 0, targetDelay = 0, calibration = 0, entralinkCalibration = 0, handheld) => {
  const stages = delayTimerFactory.createStages(targetSecond, targetDelay, calibration, handheld);
  return [stage1(stages), stage2(stages, entralinkCalibration)];
};

const calibrate = (targetDelay = 0, delayHit = 0, handheld) => {
  return delayTimerFactory.calibrate(targetDelay, delayHit, handheld);
};

const stage1 = (stages) => {
  return stages[0] + 250;
};

const stage2 = (stages, entralinkCalibration) => {
  return stages[1] - entralinkCalibration;
};

export default {
  createStages,
  calibrate,
};

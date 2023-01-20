import secondTimerFactory from "./SecondTimerFactory";
import calibrationUtil from "../util/CalibrationUtil";
import timeUtil from "../util/TimeUtil";

const createStages = (targetSecond = 0, targetDelay = 0, calibration = 0, handheld) => {
  return [stage1(targetSecond, targetDelay, calibration, handheld), stage2(targetDelay, calibration, handheld)];
};

const calibrate = (targetDelay = 0, delayHit = 0, handheld) => {
  const delta = calibrationUtil.toMillis(delayHit, handheld) - calibrationUtil.toMillis(targetDelay, handheld);
  if (Math.abs(delta) <= 167) {
    return 0.75 * delta;
  }
  return 1.0 * delta;
};

const stage1 = (targetSecond, targetDelay, calibration, handheld) => {
  return timeUtil.toMinimumLength(secondTimerFactory.createStages(targetSecond, calibration)[0] - calibrationUtil.toMillis(targetDelay, handheld));
};

const stage2 = (targetDelay, calibration, handheld) => {
  return calibrationUtil.toMillis(targetDelay, handheld) - calibration;
};

export default {
  createStages,
  calibrate,
};

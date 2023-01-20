import calibrationUtil from "../util/CalibrationUtil";

const createStages = (preTimer = 0, targetFrame = 0, calibration = 0, handheld) => {
  return [preTimer, calibrationUtil.toMillis(targetFrame, handheld) + calibration];
};

const calibrate = (targetFrame = 0, frameHit = 0, handheld) => {
  return calibrationUtil.toMillis(targetFrame - frameHit, handheld);
};

export default {
  createStages,
  calibrate,
};

import timeUtil from "../util/TimeUtil";

const createStages = (targetSecond = 0, calibration = 0) => {
  return [stage1(targetSecond, calibration)];
};

const calibrate = (targetSecond = 0, secondHit = 0) => {
  if (secondHit < targetSecond) {
    return (targetSecond - secondHit) * 1000 - 500;
  }
  if (secondHit > targetSecond) {
    return (targetSecond - secondHit) * 1000 + 500;
  }
  return 0;
};

const stage1 = (targetSecond, calibration) => {
  return timeUtil.toMinimumLength(targetSecond * 1000 + calibration + 200);
};

export default {
  createStages,
  calibrate,
};

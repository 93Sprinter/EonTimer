const MINIMUM_LENGTH = 14000;

const toMinimumLength = (value) => {
  while (value < MINIMUM_LENGTH) {
    value += 60000;
  }
  return value;
};

export default {
  toMinimumLength,
};

export const task = (callback, delay) => {
  return setTimeout(callback, delay ? delay : 0);
};

export const sleep = (millis) => {
  return new Promise((resolve) => setTimeout(resolve, millis));
};

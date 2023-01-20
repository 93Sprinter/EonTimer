const pattern = new RegExp(/^[0-9\/]+$/);

const getStages = (config) => {
  const stages = [];
  const value = config.flowTimer.value;
  if (pattern.test(value)) {
    let parts = value.split("/");
    parts = parts.filter((p) => p != "");

    for (let i = parts.length - 1; i >= 0; i--) {
      const stage = parts[i] - (parts[i + 1] || 0);
      if (stage > 0) {
        stages.push(stage);
      }
    }
  }
  return stages;
};

export default {
  getStages,
};

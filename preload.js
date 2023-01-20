const { ipcRenderer, contextBridge } = require("electron");

const WINDOW_API = {
  getConfig: () => ipcRenderer.invoke("get/config"),
  postConfig: (config) => ipcRenderer.invoke("post/config", config),
};

// Window.api
contextBridge.exposeInMainWorld("api", WINDOW_API);

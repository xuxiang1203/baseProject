package com.xuxiang.other.bean;

public class AppUpdateBean {
    /**
     * android : {"isupdate":true,"version":"1.0.1","forceupdate":false,"desc":"添加了达人研习社模块"}
     * ios : {"isupdate":true,"version":"1.0.1","forceupdate":false,"desc":"添加了达人研习社模块"}
     */

    private UpdateBean android;
    private UpdateBean ios;

    public UpdateBean getAndroid() {
        return android;
    }

    public void setAndroid(UpdateBean android) {
        this.android = android;
    }

    public UpdateBean getIos() {
        return ios;
    }

    public void setIos(UpdateBean ios) {
        this.ios = ios;
    }

    public static class UpdateBean {
        /**
         * isupdate : true
         * version : 1.0.1
         * forceupdate : false
         * desc : 添加了达人研习社模块
         */

        private boolean isupdate;
        private String version;
        private boolean forceupdate;
        private String desc;
        private String url;

        public boolean isIsupdate() {
            return isupdate;
        }

        public void setIsupdate(boolean isupdate) {
            this.isupdate = isupdate;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public boolean isForceupdate() {
            return forceupdate;
        }

        public void setForceupdate(boolean forceupdate) {
            this.forceupdate = forceupdate;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

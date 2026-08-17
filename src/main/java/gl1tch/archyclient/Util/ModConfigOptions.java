package gl1tch.archyclient.Util;


import java.util.ArrayList;
import java.util.List;

public class ModConfigOptions {
    private String autoGG;
    private boolean autoGGActive;
    private List<String> autoTPAACCEPT;
    private boolean autoTPAACCEPTActive;
    private List<String> autoTorture;
    private boolean autoTortureActive;
    private String autoSkipAdmin;
    private boolean autoSkipAdminActive;

    public ModConfigOptions() {
        this.autoGG = "GG";
        this.autoTPAACCEPT = new ArrayList<>();
        this.autoTorture = new ArrayList<>();
        this.autoSkipAdmin = "5";

        this.autoGGActive = true;
        this.autoTPAACCEPTActive = true;
        this.autoTortureActive = true;
        this.autoSkipAdminActive = false;
    }

    public String getAutoGG() {
        return this.autoGG;
    }

    public void setAutoGG(String val) {
        this.autoGG = val;
    }

    public List<String> getAutoTPAACCEPT() {
        return this.autoTPAACCEPT;
    }

    public void setAutoTPAACCEPT(List<String> val) {
        this.autoTPAACCEPT = val;
    }

    public List<String> getAutoTorture() {
        return this.autoTorture;
    }

    public void setAutoTorture(List<String> val) {
        this.autoTorture = val;
    }

    public String getAutoSkipAdmin() {
        return this.autoSkipAdmin;
    }

    public void setAutoSkipAdmin(String val) {
        this.autoSkipAdmin = val;
    }

    public boolean getAutoGGActive() {
        return this.autoGGActive;
    }

    public void setAutoGGActive(boolean val) {
        this.autoGGActive = val;
    }

    public boolean getAutoTPAACCEPTActive() {
        return this.autoTPAACCEPTActive;
    }

    public void setAutoTPAACCEPTActive(boolean val) {
        this.autoTPAACCEPTActive = val;
    }

    public boolean getAutoTortureActive() {
        return this.autoTortureActive;
    }

    public void setAutoTortureActive(boolean val) {
        this.autoTortureActive = val;
    }

    public boolean getAutoSkipAdminActive() {
        return this.autoSkipAdminActive;
    }

    public void setAutoSkipAdminActive(boolean val) {
        this.autoSkipAdminActive = val;
    }
}

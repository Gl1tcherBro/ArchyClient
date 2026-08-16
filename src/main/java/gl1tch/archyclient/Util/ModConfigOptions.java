package gl1tch.archyclient.Util;


import java.util.ArrayList;
import java.util.List;

public class ModConfigOptions {
    //The entire message
    private String autoGG;

    //CVS style
    private List<String> autoTPAACCEPT;
    private List<String> autoTorture;

    public ModConfigOptions() {
        this.autoGG = "GG";
        this.autoTPAACCEPT = new ArrayList<>();
        this.autoTorture = new ArrayList<>();
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
}

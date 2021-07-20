package com.greatplan.myapplication.Bean;

/**
 * @Author :jack
 * @Date :2021/7/14
 * @Effect :
 **/
public class RsaEncryption {

    /**
     * cipherText : FWS4ElBBeAEPAaH7QwJmSChlGkFRLLD8Ns5n0olFO/i/RKzHKC5kwPMdqVu6b+8hsPvzZYZkD/DhVcSVoRQ0SJZQ06fPiYvAcGExmt0Mqatbv+EnJghHDxQ0STkEZtZ1ABEa+hWziLmeOaUc46+wWN1xYhtNPMaFdOW1tkwKJdA=
     * message : 加密成功
     * status : 0000
     */

    private String cipherText;
    private String message;
    private String status;

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package common;

import java.io.Serializable;

/**
 * Created by bbking on 17-1-17.
 */
public class TM  implements Serializable{
    private  TT em;

    public TT getEm() {
        return em;
    }

    public TM setEm(TT em) {
        this.em = em;
        return this;
    }
}

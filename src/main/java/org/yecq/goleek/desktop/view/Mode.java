package org.yecq.goleek.desktop.view;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author yecq
 */
@Component
@Qualifier("mode")
public class Mode {

    private String mode;

    public Mode() {
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}

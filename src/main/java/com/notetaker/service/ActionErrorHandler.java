package com.notetaker.service;

import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class ActionErrorHandler {

    public static void handleFailure(Exception ex, String msg, final Logger LOG) {
        handleFailure(ex, msg, LOG, null);
    }

    public static void handleFailure(Exception ex, String msg, final Logger LOG, Component parent) {
        LOG.error(msg, ex.getCause());
        LOG.debug(msg, ex);
        if (parent != null) {
            JOptionPane.showMessageDialog(parent, msg);
        }
    }

}

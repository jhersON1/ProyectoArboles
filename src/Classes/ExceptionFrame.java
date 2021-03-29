/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author neON
 */
public class ExceptionFrame extends Exception {
    /**
     * Creates a new instance of <code>exceptionFrame</code> without detail
     * message.
     */
    public ExceptionFrame() {
    }

    /**
     * Constructs an instance of <code>exceptionFrame</code> with the specified
     * detail message.
     *
     * @param frame es el formulario donde se mostrará un mensaje como excepcion
     * @param msg the detail message.
     */
    public ExceptionFrame(JFrame frame,String msg) {
        super(msg);
        JOptionPane.showMessageDialog(frame, msg);
    }
}

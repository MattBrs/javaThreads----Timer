/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany._threadcronometro;

import java.util.Set;

/**
 *
 * @author mb
 */
public class Cronometro extends Thread {
    
    private int _milliseconds;
    private int _minutes;
    private int _seconds;
    private boolean _check;
    
    public Cronometro(){
        _milliseconds = 1000;
        _seconds = 59;
        _minutes = 9;
        _check = false;
    }
    @Override
    public void run(){
        
        synchronized (this){
        
            while(!_check) {
                _milliseconds--;
                if (_milliseconds <= 0 && _seconds > 0) {
                    _milliseconds = 1000;
                    _seconds--;
                } else if (_seconds <= 0 && _minutes > 0) {
                    _seconds = 60;
                    _minutes--;
                } else if (_minutes <= 0 && _milliseconds <= 0) {
                    _check = true;
                }
                try {
                    this.wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }   
            }

        }
    }
    
    
    public void requestStop(){
        setCheck();
        
    }


    
    public int getMilliseconds(){
        return _milliseconds;
    }
    public int getSeconds(){
        return _seconds;
    }
    public int getMinutes(){
        return _minutes;
    }
    
    public void setCheck(){
        _check = true;
    }
    
}

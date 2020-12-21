package MoTa;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;

import static MoTa.MTower.*;

import static Interface.BattleBoard.battlePane;
import static Interface.DialogBoard.dialogPane;
import static Interface.MessageBoard.msgPane;
//import static Interface.FlyBoard.flyPane;

import Interface.FlyBoard;

public class Main {
	public static void main(String[] args) {
        gamePanel = new MTower();
        gamePanel.setPreferredSize(new Dimension(1233, 888));
        
        gamePanel.add(battlePane);
        gamePanel.add(dialogPane);
        gamePanel.add(msgPane);
        //gamePanel.add(flyPane);
        
        gameFrame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!MTower.inConversation)
                    switch (e.getKeyCode()) {
                        
                        case VK_UP:     // ���� ��
                            if (myHero.getPosY() - 1 < 11 && myHero.getPosY() - 1 >= 0) {
                                myHero.setTowards(0);
                                interaction(myHero.getPosX(), myHero.getPosY() - 1);
                                gameFrame.repaint();
                            }
                            break;
                        
                        case VK_RIGHT:  // ���� ��
                            if (myHero.getPosX() + 1 < 11 && myHero.getPosX() + 1 >= 0) {
                                myHero.setTowards(1);
                                interaction(myHero.getPosX() + 1, myHero.getPosY());
                                gameFrame.repaint();
                            }
                            break;
                        
                        case VK_DOWN:   // ���� ��
                            if (myHero.getPosY() + 1 < 11 && myHero.getPosY() + 1 >= 0) {
                                myHero.setTowards(2);
                                interaction(myHero.getPosX(), myHero.getPosY() + 1);
                                gameFrame.repaint();
                            }
                            break;
                        
                        case VK_LEFT:   // ���� ��
                            if (myHero.getPosX() - 1 < 11 && myHero.getPosX() - 1 >= 0) {
                                myHero.setTowards(3);
                                interaction(myHero.getPosX() - 1, myHero.getPosY());
                                gameFrame.repaint();
                            }
                            break;
                            
                        case VK_F:      // ���� F
                            if (canFly && currentFloor != 10 && currentFloor != 20 && currentFloor != 21) {
                            	//System.out.print("press F.\n");
                                new FlyBoard();
                            }
                            break;
                            /*
                        case VK_L:      // ���� D
                            if (ItemsBean.isHasForecast) {
                                displayForecast();
                            }
                            break;*/
                    }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        gameFrame.setContentPane(gamePanel);
        gameFrame.setResizable(false);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

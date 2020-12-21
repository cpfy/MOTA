package MoTa;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.HashMap;

import Objects.Hero;
import Datas.ImageData;
import Datas.ShopData;
import Datas.MapData;
import static Datas.MapData.*;

import Interface.BattleBoard;
import Interface.DialogBoard;
import Interface.MessageBoard;
import Interface.ShopBoard;

public class MTower extends JPanel
{
	public static final int PIX69 = 69;
	
	private static final int YellowKeyID = 0;
	private static final int BlueKeyID = 1;
	private static final int RedKeyID = 2;
	
	private static int[] firstPickup = new int[100];
	
	public static JFrame gameFrame;
    public static JPanel gamePanel;

    public static int currentFloor = 0;     // ��ǰ¥��
    public static int maxFloor = 0;         // ���¥��
    
    public static boolean inConversation = false;
    public static boolean canFly = false;
    
    public static HashMap<Integer, BufferedImage> imgSource = ImageData.imageMap;     // ͼƬ
    public static Hero myHero = new Hero();                           				  // �������

    // ������
    public MTower() {
        setLayout(null);
        gameFrame = new JFrame("Cover : ����ħ��v1.42");
    }

    // ��д paintComponent() ������
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        //ƫ����
        int offsetX = 3;
        int offsetY = 2;

        // ������Ϸ������
        g2.drawImage(ImageData.gameBgImg, 0, 0, 1233, 888, null);

        // ���� ��ͼ����
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 11; y++) {
                int id = OneMap[currentFloor][x][y];
                BufferedImage a = imgSource.get(id);
                g2.drawImage(a, PIX69 * y + PIX69 * 6 + offsetX , PIX69 * x + PIX69 + offsetY, null);
            }
        }
        
        //��������/��ɫ
        g2.setFont(new Font("����", Font.BOLD, 35));
        g2.setColor(Color.WHITE);

        // ���� ���
        g2.drawImage(myHero.draw(), (myHero.getPosX() + 6) * PIX69, (myHero.getPosY() + 1) * PIX69, null);

        //�������������
        g2.drawString(myHero.getLevel() + "", 250, 205);
        g2.drawString(myHero.getHealth() + "", 220, 255);
        g2.drawString(myHero.getAttack() + "", 240, 305);
        g2.drawString(myHero.getDefence() + "", 240, 360);
        g2.drawString(myHero.getSpeed() + "", 250, 415);
        g2.drawString(myHero.getExp() + "", 250, 465);
        
        //��ǰ¥��
        g2.drawString("����   " + currentFloor + "F", 710, 45);
        
        //����״̬
        g2.drawString(myHero.getHeroStates(), 220, 155);
        
        // ����Կ��+���
        g2.setFont(new Font("����", Font.BOLD, 60));
        g2.drawString(myHero.getKey(YellowKeyID) + "", 250, 610);
        g2.drawString(myHero.getKey(BlueKeyID) + "", 250, 680);
        g2.drawString(myHero.getKey(RedKeyID) + "", 250, 750);
        g2.drawString(myHero.getGold() + "", 250, 820);
    }


    // �� �� �� �� �� ��
    public static void interaction(int x, int y) {
        int id = OneMap[currentFloor][y][x];
        
        switch (id) {
            case 0:     // ��·
                myHero.move(x, y);
                break;
                
            case 1:     // ǽ
                break;
                
            case 2:     // ����
                if (myHero.enoughKey(YellowKeyID)) 
                {
                    OneMap[currentFloor][y][x] = 0;
                    myHero.useKey(YellowKeyID);
                }
                break;
                
            case 3:     // ����
            	if (myHero.enoughKey(BlueKeyID)) 
                {
                    OneMap[currentFloor][y][x] = 0;
                    myHero.useKey(BlueKeyID);
                }
                break;
                
            case 4:     // ����
            	if (myHero.enoughKey(RedKeyID)) 
                {
                    OneMap[currentFloor][y][x] = 0;
                    myHero.useKey(RedKeyID);
                }
                break;
                
            case 5:     // ������
            	if(MapData.doorCanOpen(currentFloor)) 
            		OneMap[currentFloor][y][x] = 0;
                break;
                
            case 6:     // [����] ��Կ��
                OneMap[currentFloor][y][x] = 0;
                MTower.myHero.addKey(YellowKeyID);
                if(firstPickup[id] == 0) {
                	MessageBoard.displayMessage("��Կ��");
                	firstPickup[id]++;
                }   
                break;
                
            case 7:     // [����] ��Կ��
                OneMap[currentFloor][y][x] = 0;
                MTower.myHero.addKey(BlueKeyID);
                if(firstPickup[id] == 0) {
                	MessageBoard.displayMessage("��Կ��");
                	firstPickup[id]++;
                }   
                break;
                
            case 8:     // [����] ��Կ��
                OneMap[currentFloor][y][x] = 0;
                MTower.myHero.addKey(RedKeyID);
                if(firstPickup[id] == 0) {
                	MessageBoard.displayMessage("��Կ��");
                	firstPickup[id]++;
                }   
                break;
                
            case 9:     // [����] ����ʯ
                OneMap[currentFloor][y][x] = 0;
                MTower.myHero.addDefence(2);
                if(firstPickup[id] == 0) {
                	MessageBoard.displayMessage("����ʯ ������+2");
                	firstPickup[id]++;
                }   
                break;
                
            case 10:    // [����] �챦ʯ
                OneMap[currentFloor][y][x] = 0;
                MTower.myHero.addAttack(2);
                if(firstPickup[id] == 0) {
                	MessageBoard.displayMessage("�챦ʯ ������+2");
                	firstPickup[id]++;
                }
                break;
                
            case 11:    // [����] ��ҩˮ
                OneMap[currentFloor][y][x] = 0;
                MTower.myHero.addHealth(150);
                if(firstPickup[id] == 0) {
                    MessageBoard.displayMessage("СѪƿ ����ֵ+150");
                	firstPickup[id]++;
                }
                break;
                
            case 12:    // [����] ��ҩˮ
                OneMap[currentFloor][y][x] = 0;
                MTower.myHero.addHealth(500);
                if(firstPickup[id] == 0) {
                    MessageBoard.displayMessage("��Ѫƿ ����ֵ+500");
                	firstPickup[id]++;
                }
                break;
                
            case 13:    // ��¥
            	flyPos[currentFloor][0] = x;
            	flyPos[currentFloor][1] = y;
            	
                currentFloor++;
                maxFloor = Math.max(maxFloor, currentFloor);
                myHero.move(x, y);
                break;
                
            case 14:    // ��¥
            	flyPos[currentFloor][0] = x;
            	flyPos[currentFloor][1] = y;
            	
                currentFloor--;
                myHero.move(x, y);
                break;
                
            case 15:    // ������ʧ�Ļ���
            	OneMap[currentFloor][y][x] = 0;
                break;
            
            case 16:
            	OneMap[currentFloor][y][x] = 0;
                myHero.move(x, y);
            	myHero.addSpeed(1);
            	if(firstPickup[id] == 0) {
                	MessageBoard.displayMessage("�̱�ʯ ����+1");
                	firstPickup[id]++;
                }
            	break;
            
            case 17:	//20F����ɫǽ
            	break;
            
            case 19:    // ��
                break;
                
            case 20:    // �ǿ�
                break;
                
            case 22:    // �̵�
            	ShopBoard.shop(id);
            	break;
            	
            case 24:    // [�Ի�] ����
                new DialogBoard(id, x, y);
                OneMap[currentFloor][y][x] = 0;
                break;
                
            case 25:    // [�Ի�] С͵
            case 26:    // [�Ի�] ����
            case 27:    // [�Ի�] ����
            	if(ShopData.isShopper(id,currentFloor)) 
            		ShopBoard.shop(id);
            	else new DialogBoard(id, x, y);
                break;
            
            case 28:    // [�Ի�] ����
            	if(currentFloor == 20) {
            		new DialogBoard(id, x, y);
            		OneMap[currentFloor][y+3][x] = 1;
            	}
            	
            	else if(currentFloor == 21) {
            		new DialogBoard(id, x, y);
            	}
            	
            	break;
                
            case 30:    // [����] Ծ��֮��
                OneMap[currentFloor][y][x] = 0;
                myHero.addLevel(1);
                myHero.addHealth(400);
                myHero.addAttack(5);
                myHero.addDefence(5);
                MessageBoard.displayMessage("Ծ��֮�� �ȼ�+1");
                break;
                
            case 31:    // [����] �����
                OneMap[currentFloor][y][x] = 0;
                myHero.addLevel(3);
                myHero.addHealth(3000);
                myHero.addAttack(21);
                myHero.addDefence(21);
                MessageBoard.displayMessage("����� �ȼ�+3");
                break;
                /*
            case 32:    // [����] ����ʮ�ּ�
                OneMap[currentFloor][y][x] = 0;
                myHero.move(x, y);
                //ItemsBean.isHasCross = true;
                //MessageBoard.displayMessage("������ʮ�ּܡ� �������������е����ӣ����Խ������������������һЩ������������������ֵ����");
                break;
            case 33:    // [����] ʥˮƿ
                OneMap[currentFloor][y][x] = 0;
                myHero.move(x, y);
                MTower.myHero.addHealth(myHero.getHealth());
                //MessageBoard.displayMessage("��ʥˮƿ�� �����Խ������������һ��������ֵ�ӱ�����");
                break;
            case 34:    // [����] ʥ���
                OneMap[currentFloor][y][x] = 0;
                myHero.move(x, y);
                //ItemsBean.isHasForecast = true;
                //MessageBoard.displayMessage("��ʥ��ա� �� L ��ʹ�� �鿴����Ļ��������");
                break;
            case 35:    // [����] ��֮����
                OneMap[currentFloor][y][x] = 0;
                myHero.move(x, y);
                //ItemsBean.isHasJump = true;
                //MessageBoard.displayMessage("����֮���̡� �� J ��ʹ�� ���Ѿ��߹���¥��������Ծ��");
                break;
            case 36:    // [����] ħ��֮��
                OneMap[currentFloor][y][x] = 0;
                myHero.addKey(YellowKeyID);
                myHero.addKey(BlueKeyID);
                myHero.addKey(RedKeyID);
                MessageBoard.displayMessage("ħ��֮�� ����Կ����+1");
                break;*/
            case 38:    // [����] ��ʯ��ͷ
                OneMap[currentFloor][y][x] = 0;
                //ItemsBean.isHasHammer = true;
                MessageBoard.displayMessage("��ʯ��ͷ");
                break;
                
            case 39:    // [����] ���˽��
                OneMap[currentFloor][y][x] = 0;
                MTower.myHero.addGold(200);
                MessageBoard.displayMessage("���˽�� ���+300");
                break;
                
            case 40:    // [���� monster]
            case 41:    // [���� monster]
            case 42:    // [���� monster]
            case 43:    // [���� monster]
            case 44:    // [���� monster]
            case 45:    // [���� monster]
            case 46:    // [���� monster]
            case 47:    // [���� monster]
            case 48:    // [���� monster]
            case 49:    // [���� monster]
            case 50:    // [���� monster]
            case 51:    // [���� monster]
            case 52:    // [���� monster]
            case 53:    // [���� monster]
            case 54:    // [���� monster]
            case 55:    // [���� monster]
            case 56:    // [���� monster]
            case 57:    // [���� monster]
            case 58:    // [���� monster]
            case 59:    // [���� monster]
            case 60:    // [���� monster]
            case 61:    // [���� monster]
            case 62:    // [���� monster]
            case 63:    // [���� monster]
            case 64:    // [���� monster]
            case 65:    // [���� monster]
            case 66:    // [���� monster]
            case 67:    // [���� monster]
            case 68:    // [���� monster]
            case 69:    // [���� monster]
            case 70:    // [���� monster]
                    new BattleBoard(id, x, y);
                    break;
            
            case 71:    // [����] ����
                OneMap[currentFloor][y][x] = 0;
                MTower.myHero.addAttack(8);
                MessageBoard.displayMessage("���� ������+8");
                break;
            
            case 72:    // [����] ����
                OneMap[currentFloor][y][x] = 0;
                MTower.myHero.addAttack(16);
                MessageBoard.displayMessage("���� ������+16");
                break;
            
            case 76:    // [����] Ƥ��֮��
                OneMap[currentFloor][y][x] = 0;
                myHero.addDefence(7);
                MessageBoard.displayMessage("Ƥ��֮�� ������+7");
                break;
            
            case 77:    // [����] ����
                OneMap[currentFloor][y][x] = 0;
                myHero.addDefence(13);
                MessageBoard.displayMessage("���� ������+13");
                break;
            
            case 78:    // [����] ��֮��
                OneMap[currentFloor][y][x] = 0;
                myHero.addDefence(22);
                MessageBoard.displayMessage("��֮�� ������+22");
                break;
            
            case 81:
            	OneMap[currentFloor][y][x] = 0;
            	myHero.setHeroStates(0);
            	MessageBoard.displayMessage("��ƣ�����˥��״̬");
            	break;
            
            case 82:
            	OneMap[currentFloor][y][x] = 0;
            	myHero.setHeroStates(0);
            	MessageBoard.displayMessage("�������������ж�״̬");
            	break;
            
            case 198:
                new BattleBoard(id, x, y);
                break;
        }
    }
}

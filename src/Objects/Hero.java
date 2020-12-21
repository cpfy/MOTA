package Objects;

import java.awt.image.BufferedImage;
import Datas.ImageData;

public class Hero {
	enum HeroState	//Ӣ��״̬
	{
		normal, died, poisoned, weak 
	}
	
	private int health;		//����ֵ
	private int attack;		//������
	private int defence;	//������
	private int speed;		//���ݣ��Ⱥ���/���ܣ�
	private int gold;		//���
	private int exp;		//����
	private double critical;	//������
	private int[] keys = new int[3];		//Կ��
	private HeroState s1 = HeroState.normal;		//��ǰ״̬
	
	private int level;		//�ȼ�
	public int posX;		//λ��
	public int posY;
	private int towards;	//����: 0=��, 1=��, 2=��, 3=��
	
	//��ʼ�����췽��
    public Hero() 
    {
        this.health = 50000;
        this.attack = 100;
        this.defence = 100;
        this.speed = 2;
        this.gold = 0;
        this.exp = 0;
        this.critical = 0.1;
        
        this.keys[0] = 1;	//��Կ��
        this.keys[1] = 1;	//��Կ��
        this.keys[2] = 1;	//��Կ��
        
        this.level = 1;
        this.posX = 5;
        this.posY = 9;
        this.towards = 0;
    }
    
    //���Ի�ȡ���
    public int getHealth() 
	{
        return health;
    }

    public int getAttack()
    {
        return attack;
    }

    public int getDefence()
    {
        return defence;
    }
    
    public int getSpeed() 
    {
    	return speed;
    }

    public int getGold()
    {
        return gold;
    }
    
    public int getExp()
    {
        return exp;
    }

    public double getCritical() 
    {
        return critical;
    }
    
    public int getKey(int keyID)
    {
    	return keys[keyID];
    }
    
    public int getLevel() 
    {
        return level;
    }
    
    public int getPosX() 
    {
        return posX;
    }
    
    public int getPosY() 
    {
        return posY;
    }
    
    public int getTowards()
    {
    	return towards;
    }
    
    public String getHeroStates()
    {
    	switch(s1)
    	{
	    	case normal: return "����";
	    	case died: return "����";
	    	case poisoned: return "�ж�";
	    	case weak: return "����";
	    	default: return "Error";
    	}
    }
    
    //set���
    public void setHealth(int health)
    {
        this.health = health;
    }

    public void setAttack(int attack) 
    {
    	this.attack = attack;
    }
    
    public void setDefence(int defence)
    {
        this.defence = defence;
    }
    
    public void setSpeed(int speed)
    {
    	this.speed = speed;
    }
    
    public void setGold(int gold)
    {
    	this.gold = gold;
    }
    
    public void setExp(int exp)
    {
    	this.exp = exp;
    }
    
    public void setCritical(int critical)
    {
    	this.critical = critical;
    }
    
    public void setKey(int keyID, int keyNum)
    {
    	this.keys[keyID] = keyNum; 
    }
    
    public void setLevel(int level)
    {
    	this.level = level;
    }
    
    //λ��˲�ƴ�����ز���
    
    public void setPosX(int posX)
    {
    	this.posX = posX;
    }
    
    public void setPosY(int posY)
    {
    	this.posY = posY;
    }
    
    public void setTowards(int towards)
    {
    	this.towards = towards;
    }
    
    public void setHeroStates(int a)
    {
    	switch(a)
    	{
	    	case 0:
	    		this.s1 = HeroState.normal;
	    		break;
	    	case 1:
	    		this.s1 = HeroState.died;
	    		break;
	    	case 2:
	    		this.s1 = HeroState.poisoned;
	    		break;
	    	case 3:
	    		this.s1 = HeroState.weak;
	    		break;
    	}
    }
    
    //����Ϊ�����޸����
    
    public void addHealth(int h)
    {
    	health += h;
    }
    
    public void loseHealth(int damage) 
    {
    	if(health - damage <= 0) 
    	{
    		s1 = HeroState.died;
    	}
    	else 
    	{
    		health -= damage;
    	}
    }
    
    
    public void addAttack(int atk)
    {
    	attack += atk;
    }
    
    public void addDefence(int def)
    {
    	defence += def;
    }
    
    public void addSpeed(int sp)
    {
    	speed += sp;
    }
    
    public void addLevel(int l)
    {
    	level += l;
    }
	
    public void addGold(int g)
    {
    	gold += g;
    }
    
    public void spendGold(int g)
    {
    		gold -= g;
    }
    
    public boolean enoughGold(int g)
    {
    	if(g > gold) 
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }
    
    public void addExp(int e)
    {
    	exp += e;
    }
    
    public void spendExp(int e)
    {
    		exp -= e;
    }
    
    public boolean enoughExp(int e)
    {
    	if(e > exp) 
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }
    
    public void addKey(int keyID)
    {
    	keys[keyID] += 1; 
    }
    
    public boolean enoughKey(int keyID)
    {
    	if(keys[keyID] == 0)
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }
    
    public void useKey(int keyID)
    {
    	keys[keyID] -= 1; 
    }
    
    //�ƶ����
    
    public void move(int x, int y)
    {
    	posX = x;
    	posY = y;
    }
    
    //��ͼ���
    public BufferedImage draw()
    {
    	return ImageData.playerMap.get(towards);
    }

}

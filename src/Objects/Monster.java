package Objects;

//import Objects.Hero.HeroState;

public class Monster {
	enum LifeState	//����״̬
	{
		alive, died
	}
	
	private int id;			//�������
	private String name;	//����
	private int health;		//����ֵ
	private int attack;		//������
	private int defence;	//������
	private int speed;		//���ݣ��Ⱥ���/����/����Ƶ�ʣ�
	private int gold;		//������
	private int exp;		//���侭��
	private LifeState s = LifeState.alive;		//��ǰ״̬
	
	public Monster(int id, String name, int health, int attack, int defence, int speed, int gold, int exp) 
	{
        this.id = id;
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
        this.gold =  gold;
        this.exp = exp;
    }
	
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

    public String getName() 
    {
        return name;
    }

}

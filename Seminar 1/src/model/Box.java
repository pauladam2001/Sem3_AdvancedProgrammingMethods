package model;

public class Box implements Repository{
    private final Weightable[] items;
    public Box(Weightable... items)
    {
        this.items=items;
    }
    @Override
    public Weightable[] getHeavierThan200() {
        Weightable[] filtered=new Weightable[items.length];
        int offset=0;
        for(int i=0;i< items.length;i++)
        {
            if(items[i].getWeight()>200)
                filtered[offset++]=items[i];
        }
        Weightable[] returned=new Weightable[offset];
        System.arraycopy(filtered,0,returned,0,offset);
        return returned;

    }
}

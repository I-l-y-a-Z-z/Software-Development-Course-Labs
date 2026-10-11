package problem2;

public class IntegerList
{
    int[] list; //values in the list
    int numElements;
    int size;
    //-------------------------------------------------------
//create a list of the given size
//-------------------------------------------------------
    public IntegerList(int size)
    {
        list = new int[size];
        this.size = size;
        numElements = size;
    }
    public void increaseSize()
    {
        int newSize = size * 2;
        if (newSize == 0) {newSize = 1;}
        int[] newList = new int[newSize];
        for (int i=0; i<numElements; i++)
            newList[i] = list[i];
        list = newList;
        size = newSize;
    }
    public void addElement(int newVal)
    {
        if (numElements == size) {increaseSize();}
        list[numElements] = newVal;
        numElements++;
    }
    public void removeFirst(int newVal)
    {
        for (int i=0; i<numElements; i++)
        {
            if (list[i] == newVal)
            {
                for (int j=i; j<numElements-1; j++)
                    list[j] = list[j+1];
                numElements--;
                break;
            }
        }
    }
    public void removeAll(int newVal)
    {
        for (int i=0; i<numElements; i++)
        {
            if (list[i] == newVal)
            {
                for (int j=i; j<numElements-1; j++)
                    list[j] = list[j+1];
                numElements--;
                i--;
            }
        }
    }
    //-------------------------------------------------------
//fill array with integers between 1 and 100, inclusive
//-------------------------------------------------------
    public void randomize()
    {
        for (int i=0; i<list.length; i++)
            list[i] = (int)(Math.random() * 100) + 1;
        numElements = size;
    }
    //-------------------------------------------------------
//print array elements with indices
//-------------------------------------------------------
    public void print()
    {
        for (int i=0; i<numElements; i++)
            System.out.println(i + ":\t" + list[i]);
    }
}

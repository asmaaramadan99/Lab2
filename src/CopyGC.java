import java.util.ArrayList;

public class CopyGC implements GarbageCollector{

    private ArrayList<INode> roots = new ArrayList<>();

    public ArrayList<INode> getCopied() {
        return copied;
    }

    public void setCopied(ArrayList<INode> copied) {
        this.copied = copied;
    }

    private ArrayList<INode> copied = new ArrayList<>();


    public ArrayList<INode> getRoots() {
        return roots;
    }

    public void setRoots(ArrayList<INode> roots) {
        this.roots = roots;
    }

    @Override
    public void garbageCollect() {
        copy();
        collect();
    }
    public void copy(){
        for (int i=0;i<roots.size();i++){
            copied.add(roots.get(i));
        }
        int i=0;
        while (i < copied.size()){
            for (int j=0;j< copied.get(i).getChildren().size();j++){
                INode n=copied.get(i).getChildren().get(j);
                if (! copied.contains(n))
                    copied.add(n);
            }
            i++;
        }
    }
    public void collect(){
        copied.get(0).setHeapStartIndex(0);
        copied.get(0).setHeapEndIndex(copied.get(0).getSize());
        for (int i=1;i<copied.size();i++){
            copied.get(i).setHeapStartIndex(1+copied.get(i-1).getHeapEndIndex());
            copied.get(i).setHeapEndIndex(copied.get(i).getHeapStartIndex()+(copied.get(i).getSize()));
        }
    }
}

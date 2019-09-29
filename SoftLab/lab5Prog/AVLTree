import java.util.LinkedList;
import java.util.Queue;
import java.util.NoSuchElementException;
/**
 * @author Brij Raj Kishore
 */


public class AVLTree<T extends Comparable<T>>{
    private Node<T> root;

    /**
     * @param <T> Generic Type Parameter Of AVL Class Type
     */
    private class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
        private T data;
        private Node<T> left;
        private Node<T> right;
        private int height;
        private int size;
        private  Node() { }

        private Node(T data) {
            this.data = data;
            this.setLeft(null);
            this.setRight(null);
            this.setHeight(0);
            this.setSize(1);
        }
        private T getData() {
            return data;
        }

        private void setData(T data) {
            this.data = data;
        }

        private Node<T> getLeft() {
            return left;
        }

        private void setLeft(Node<T> left) {
            this.left = left;
        }

        private Node<T> getRight() {
            return right;
        }

        private void setRight(Node<T> right) {
            this.right = right;
        }

        private void setHeight(int height) {
            this.height = height;
        }

        private int getHeight() { return height; }

        public int getSize() { return size; }

        private void setSize(int size) { this.size = size; }

        @Override
        public int compareTo(Node<T> o) {
            return this.data.compareTo(o.data);
        }
        @Override
        public String toString() {
            return this.data+"";
        }

    }

    public AVLTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int height() {
        return height(root);
    }

    public int size() {
        return size(root);
    }

    private int size(Node<T> x) {
        if(x == null) return 0;
        return x.getSize();
    }

    private int height(Node<T> x) {
        if(x == null) return -1;
        return x.getHeight();
    }

    public Node<T> get(T data) {
         if(data == null) throw new IllegalArgumentException();
        return get(root, data);
    }

    private Node<T> get(Node<T> x, T data) {
        if(x == null) return null;
        int cmp = x.getData().compareTo(data);
        if(cmp > 0){
            return get(x.getLeft(), data);
        }
        else if(cmp < 0){
            return get(x.getRight(), data);
        }
        else
            return x;
    }
    public boolean contains(T data) {
        return get(data) != null;
    }

    public void insert(T data) {
        if(data == null) throw new IllegalArgumentException();

        root = insert(root, data);
        assert check();
    }

    private Node<T> insert(Node<T> x, T data) {
        if(x == null) return new Node<T>(data);

        int cmp = data.compareTo(x.getData());
        if(cmp < 0) {
            x.setLeft(insert(x.getLeft(), data));
        }
        else if(cmp > 0) {
            x.setRight(insert(x.getRight(), data));
        }
        else {
            return x;
        }

        x.setSize(1 + size(x.getLeft()) + size(x.getRight()));

        x.setHeight(1 + Math.max(height(x.getLeft()),
                height(x.getRight())));
            
        return balance(x);
    }

    public Node<T> getRoot() {
        return root;
    }

    private Node<T> balance(Node<T> x){
        if(balanceFactor(x) < -1) {
            if(balanceFactor(x.getRight()) > 0) {
                x.setRight(rotateRight(x.getRight()));
            }
            x = rotateLeft(x);
        }
        else if(balanceFactor(x) > 1) {
            if (balanceFactor(x.getLeft()) < 0) {
                x.setLeft(rotateLeft(x.getLeft()));
            }
            x = rotateRight(x);
        }

        return x;
    }

    private int balanceFactor(Node<T> x) {
        return height(x.getLeft()) - height(x.getRight());
    }

    private Node<T> rotateLeft(Node<T> x) {

        Node<T> y = x.getRight();
        x.setRight(y.getLeft());
        y.setLeft(x);
        y.setSize(x.getSize());
        x.setSize(1 + size(x.getLeft()) + size(x.getRight()));
        x.setHeight(1 + Math.max(height(x.getLeft()),
                height(x.getRight())));
        y.setHeight(1 + Math.max(height(y.getLeft()),
                height(x.getRight())));

        return y;

    }

    private Node<T> rotateRight(Node<T> x) {
        Node<T> y = x.getLeft();
        x.setLeft(y.getRight());
        y.setRight(x);

        y.setSize(x.getSize());
        x.setSize(1 + size(x.getLeft()) + size(x.getRight()));

        x.setHeight(1 + Math.max(height(x.getLeft()),
                height(x.getRight())));
        y.setHeight(1 + Math.max(height(y.getLeft()),
                height(x.getRight())));

        return y;
    }

    public void delete(T data){
        if(data == null) throw new IllegalArgumentException();

        if(!contains(data)) return;

        root = delete(root , data);
        assert check();

    }

    private Node<T> delete(Node<T> x, T data) {
        int cmp = data.compareTo(x.getData());
        if(cmp < 0) {
            x.setLeft(delete(x.getLeft(), data));
        }
        else if(cmp > 0) {
            x.setRight(delete(x.getRight(), data));
        }
        else {
            if(x.getLeft() == null) {
                return x.getRight();
            }
            else if(x.getRight() == null) {
                return x.getLeft();
            }
            else {
                Node<T> y = x;
                x = max(y.getLeft());
                x.setLeft(deleteMax(y.getLeft()));
                x.setRight(y.getRight());
            }
        }

        x.setSize(1 + size(x.getLeft()) + size(x.getRight()));

        x.setHeight(1 + Math.max(height(x.getLeft()),
                height(x.getRight())));
        return balance(x);
    }



    public T min() {
        if(isEmpty()) throw  new NoSuchElementException();
        return min(root).getData();
    }

    private Node<T> min(Node<T> x) {
        if(x.getLeft() == null) return x;
        return min(x.getLeft());
    }


    public void deleteMin() {
        if(isEmpty()) throw new NoSuchElementException();
        root = deleteMin(root);
        assert check();
    }

    private Node<T> deleteMin(Node<T> x) {

        if(x.getLeft() == null) return x.getRight();

        x.setLeft(deleteMin(x.getLeft()));
        x.setSize(1 + size(x.getLeft()) + size(x.getRight()));
        x.setHeight(1 + Math.max(height(x.getLeft()),
                height(x.getRight()))) ;
        return balance(x);
    }

    public T max() {
        if(isEmpty()) throw  new NoSuchElementException();

        return max(root).getData();
    }
    private Node<T> max(Node<T> x) {
        if (x == null) {
            return x;
        }

        if(x.getRight() == null) {
            return x;
        }

        return max(x.getRight());
    }

    public void deleteMax() {
        if(isEmpty()) throw new NoSuchElementException();
        root = deleteMax(root);
        assert check();
    }

    private Node<T> deleteMax(Node<T> x) {

        if(x.getRight() == null) return x.getLeft();
        x.setRight(deleteMax(x.getRight()));

        x.setSize(1 + size(x.getLeft()) + size(x.getRight()));
        x.setHeight(1 + Math.max(height(x.getLeft()),
                height(x.getRight()))) ;

        return balance(x);
    }

    public T select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException("k is not in range 0-" + (size() - 1));
        Node<T> x = select(root, k);
        return x.getData();
    }

    private Node<T> select(Node<T> x, int k) {
        if(x == null) return null;
        int t = size(x.getLeft());
        if(t > k) return select(x.getLeft(), k);
        else if(t < k) return select(x.getRight(), k - t - 1);
        else return x;
    }

    public T floor(T data) {
    	if(data == null) throw new IllegalArgumentException("argument to floor is null");
    	if(isEmpty()) throw new NoSuchElementException("called floor with empty Tree");
        Node<T> x = floor(root, data);
        if(x == null) return null;
        else return x.getData();
    }

    private Node<T> floor(Node<T> x, T data) {
        if(x == null) return  null;
        int cmp = data.compareTo(x.getData());
        if(cmp == 0) return x;
        if(cmp < 0) return floor(x.getLeft(), data);
        Node<T> y = floor(x.getRight(), data);
        if(y != null) return y;
        else return x;
    }

    public T ceiling(T data) {
        if(data == null) throw new IllegalArgumentException("argument to floor is null");
        if(isEmpty()) throw new NoSuchElementException("called floor with empty Tree");

        Node<T> x = ceiling(root, data);
        if(x == null) return null;
        else return x.getData();
    }

    private Node<T> ceiling(Node<T> x, T data) {
        if(x == null) return  null;
        int cmp = data.compareTo(x.getData());
        if(cmp == 0) return x;
        if(cmp > 0) return ceiling(x.getLeft(), data);
        Node<T> y = ceiling(x.getRight(), data);
        if(y != null) return y;
        else return x;
    }

    public int rank(T data) {
        if(data == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(root, data);
    }
    private int rank(Node<T> x, T data) {
        if(x == null) return 0;
        int cmp = data.compareTo(x.getData());
        if(cmp < 0) return rank(x.getLeft(), data);
        else if(cmp > 0) return 1 + size(x.getLeft()) + rank(x.getRight(), data);
        else return size(x.getLeft());
    }
    public Iterable<T> treeInOrder() {
        Queue<T> queue = new LinkedList<T>();
        treeInOrder(root, queue);
        return queue;
    }
    private void treeInOrder(Node<T> x, Queue<T> queue){
        if(x == null) return;

        treeInOrder(x.getLeft(), queue);
        queue.add(x.getData());
        treeInOrder(x.getRight(), queue);

    }

    public Iterable<T> treeLevelOrder() {
        Queue<T> queue = new LinkedList<T>();
        if(!isEmpty()){
            Queue<Node<T>> q = new LinkedList<Node<T>>();
            q.add(root);
            while (!q.isEmpty()) {
                Node<T> x = q.poll();
                if(x.getLeft() != null) {
                    q.add(x.getLeft());
                }
                if(x.getRight() != null) {
                    q.add(x.getRight());
                }
            }
        }
        return queue;
    }

    public Iterable<T> dataRangeTraversal(T lo, T hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");
        Queue<T> queue = new LinkedList<T>();
        dataRangeTraversal(root, queue, lo, hi);
        return queue;
    }

    private void dataRangeTraversal(Node<T> x, Queue<T> queue,
                                    T lo, T hi) {
        if(x == null) return;

        int cmplo = lo.compareTo(x.getData());
        int cmphi = hi.compareTo(x.getData());
        if(cmplo < 0) dataRangeTraversal(x.getLeft(), queue, lo,  hi);
        if(cmplo <= 0 && cmphi >= 0) queue.add(x.getData());
        if(cmphi > 0) dataRangeTraversal(x.getRight(), queue, lo, hi);
    }

    public int size(T lo, T hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }


    private boolean check(){
        if (!isBST()) System.out.println("Symmetric order not consistent");
        if (!isAVL()) System.out.println("AVL property not consistent");
        if (!isSizeConsistent()) System.out.println("Subtree counts not consistent");
        if (!isRankConsistent()) System.out.println("Ranks not consistent");
        return isBST() && isAVL() && isSizeConsistent() && isRankConsistent();
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }
    private boolean isBST(Node<T> x, T min, T max) {
        if (x == null) return true;
        if (min != null && x.getData().compareTo(min) <= 0) return false;
        if (max != null && x.getData().compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.getData()) && isBST(x.right, x.getData(), max);
    }

    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node<T> x) {
        if (x == null) return true;
        if (x.size != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    private boolean isAVL() {
        return isAVL(root);
    }

    private boolean isAVL(Node x) {
        if (x == null) return true;
        int bf = balanceFactor(x);
        if (bf > 1 || bf < -1) return false;
        return isAVL(x.getLeft()) && isAVL(x.getRight());
    }

    private boolean isRankConsistent() {
        for (int i = 0; i < size(); ++i)
            if(i != rank(select(i))) return false;

        for (T data : treeInOrder())
            if(data.compareTo(select(rank(data)))!= 0) return false;

        return true;

    }

    void printTree() {
        if(isEmpty()) {    System.out.println("Tree Is Empty"); return; }

        Queue<Node<T>> queue = new LinkedList<Node<T>>();

        Node<T> marker = new Node<T>();

        queue.add(root);
        queue.add(marker);

        while (queue.size() > 1) {
            Node<T> currNode = queue.poll();

            if(currNode == marker) {
                System.out.println();
                queue.add(marker);
                continue;
            }

            System.out.print("N : "+ currNode.getData() +" ");

            if(currNode.getLeft() != null) {
                System.out.print("L : " + currNode.getLeft().getData() +" ");
                queue.add(currNode.getLeft());
            }
            else {
                System.out.print("L : " + null +" ");
            }
            if(currNode.getRight() != null) {
                System.out.print("R : " + currNode.getRight().getData() +" | " );
                queue.add(currNode.getRight());
            }
            else {
                System.out.print("R : " + null +" | ");
            }
        }
    }

    public void printInOrderTree() {
        printInOrderTree(root);
    }

    private void printInOrderTree(Node<T> x) {
        if (x == null) return;

        printInOrderTree(x.getLeft());

        System.out.print(x.getData() + " ");

        printInOrderTree(x.getRight());
    }
}

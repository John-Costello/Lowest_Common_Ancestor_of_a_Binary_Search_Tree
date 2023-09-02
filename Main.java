import java.util.Arrays;

public class Main
{   
    public static void main(String[] args)
    {
        System.out.println("The initial array with the binary tree node values.");
        int[] nodeArray = new int[]{4,2,7,1,3,6,9,8,10,11,12,13,15,16,18};
        System.out.println(Arrays.toString(nodeArray)+"\n");
        
        System.out.println("The binary tree node values as a two dimensional array.");
        int[][] d2nodeArray = generateD2NodeArray(nodeArray);
        System.out.println(Arrays.deepToString(d2nodeArray)+"\n");
        
        System.out.println("The binary tree node objects as a two dimensional array.");
        TreeNode[][] binaryTree = generateBinaryTree(d2nodeArray);
        System.out.println(Arrays.deepToString(binaryTree)+"\n"); 
        
        System.out.println("The positions of the TreeNode objects of the binary tree.");
        TreeNode rootTreeNode = binaryTree[0][0];
        binaryTree = null;
        displayNodes(rootTreeNode);
        System.out.println();
        
        System.out.println("The TreeNode objects:");
        TreeNode p = getTreeNode(rootTreeNode,4,5);
        System.out.println("Node p: "+p+"\n");
        TreeNode q = getTreeNode(rootTreeNode,4,8);
        System.out.println("Node q: "+q+"\n");
        
        System.out.print("The Lowest Common Ancestor of nodes p and q is ");
        System.out.println(new Solution().lowestCommonAncestor(rootTreeNode, p, q));
    }
    
    static TreeNode[][] generateBinaryTree(int[][] d2nodeArray)
    {
        int numLevels = d2nodeArray.length;
        TreeNode[][] d2treeNodeArray = new TreeNode[numLevels][];
        for(int i=numLevels-1; i==numLevels-1 && i>=0; i--)
        {
            TreeNode[] levelTreeNodeArray = new TreeNode[d2nodeArray[i].length];
            for(int j=0; j<d2nodeArray[i].length; j++)
            {
                levelTreeNodeArray[j]=new TreeNode(d2nodeArray[i][j]);
            }
            d2treeNodeArray[i]=levelTreeNodeArray;
        }
        for(int i=numLevels-2; i>=0; i--)
        {
            TreeNode[] levelTreeNodeArray = new TreeNode[d2nodeArray[i].length];
            for(int j=0; j<d2nodeArray[i].length; j++)
            {
                if(2*j+1<d2nodeArray[i+1].length)
                {
                   levelTreeNodeArray[j]=new TreeNode(d2nodeArray[i][j],d2treeNodeArray[i+1][2*j],d2treeNodeArray[i+1][2*j+1]);
                }
                else if(2*j+1==d2nodeArray[i+1].length)
                {
                    levelTreeNodeArray[j]=new TreeNode(d2nodeArray[i][j],d2treeNodeArray[i+1][2*j],null);
                }
                else
                {
                    levelTreeNodeArray[j]=new TreeNode(d2nodeArray[i][j],null,null);
                }
            }
            d2treeNodeArray[i]=levelTreeNodeArray;
        }
        return d2treeNodeArray;
    }
    
    static int[][] generateD2NodeArray(int[] nodeArray)
    {
        int nodeArrayLength = nodeArray.length;
        int level=0;
        int levelNodeQty=0;
        int numNodes=0;
        if(numNodes<nodeArrayLength)
        {   
            level=1; levelNodeQty=1; numNodes=1; 
        }
        while(numNodes<nodeArrayLength)
        {
            level++;
            levelNodeQty*=2;
            numNodes+=levelNodeQty;
        }  
        int[][] D2NodeArray = new int[level][];
        level=0;
        levelNodeQty=0;
        numNodes=0;
        if(numNodes<nodeArrayLength)
        {   
            level=1; levelNodeQty=1; numNodes=1; 
            D2NodeArray[level-1] = subArray(nodeArray,numNodes-levelNodeQty,levelNodeQty);
        }
        while(numNodes<nodeArrayLength)
        {
            level++;
            levelNodeQty*=2;
            numNodes+=levelNodeQty;
            D2NodeArray[level-1] = subArray(nodeArray,numNodes-levelNodeQty,numNodes);
        }
        return D2NodeArray;
    }
    
    static int[] subArray(int[] array, int a, int b)
    {
        if(array!=null)
        {
            int lenArray=array.length;
            if(a>b){   throw new RuntimeException();   }
            if(a>lenArray) a=lenArray;
            if(b>lenArray) b=lenArray;
            int lenNewArray = b-a;
            int[] newArray= new int[lenNewArray];
            for(int i=a; i<b; i++)
            {
                newArray[i-a]=array[i];    
            }
            return newArray;
        }
        else 
        {
            return null;
        }
    }
    
    static void displayNodes(TreeNode node)
    {
        class Recursion
        {
            int iLoc=1;
            int jLoc=1;
            void displayNodes(TreeNode node)
            {
                System.out.println("i: "+iLoc+" j: "+jLoc+" node: "+node);
                if(node.left!=null)
                {
                    iLoc++; jLoc=(jLoc*2)-1;
                    displayNodes(node.left);
                    iLoc--; jLoc=((jLoc+1)/2);
                }
                if(node.right!=null)
                {
                    iLoc++; jLoc=(jLoc*2);
                    displayNodes(node.right);
                    iLoc--; jLoc=(jLoc/2);
                }
            }
        }
        new Recursion().displayNodes(node);
    }
    
    static TreeNode getTreeNode(TreeNode node, int iToGet, int jToGet)
    {       
        class Recursion
        {
            TreeNode foundTreeNode = null;
            int iLoc=1;
            int jLoc=1;
            TreeNode getTreeNode(TreeNode node, int iToGet, int jToGet)
            {              
                if(iLoc==iToGet && jLoc==jToGet)
                {
                    foundTreeNode= node;
                }
                if(node.left!=null)
                {
                    iLoc++; jLoc=(jLoc*2)-1;
                    getTreeNode(node.left, iToGet, jToGet);
                    iLoc--; jLoc=((jLoc+1)/2);
                }
                if(node.right!=null)
                {
                    iLoc++; jLoc=(jLoc*2);
                    getTreeNode(node.right, iToGet, jToGet);
                    iLoc--; jLoc=(jLoc/2);
                }
                return foundTreeNode;
            }
        }
        return new Recursion().getTreeNode(node, iToGet, jToGet);
    }
}



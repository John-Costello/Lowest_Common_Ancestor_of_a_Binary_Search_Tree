class Solution
{
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        int[] pLocation, qLocation;
        int pi, pj, qi, qj;
        TreeNode pAncestor, qAncestor;
        
        class FindLocation
        {
            int[] location=null;
            int iLoc=1;
            int jLoc=1;
            
            int[] findLocation(TreeNode node, TreeNode findNode)
            {
                if(node==findNode)
                {
                    location=new int[]{iLoc, jLoc};
                }
                if(node.left!=null)
                {
                    iLoc++; jLoc=(jLoc*2)-1;
                    findLocation(node.left, findNode);
                    iLoc--; jLoc=((jLoc+1)/2);
                }
                if(node.right!=null)
                {
                    iLoc++; jLoc=(jLoc*2);
                    findLocation(node.right, findNode);
                    iLoc--; jLoc=(jLoc/2);
                }
                return location;
            }
        }
        class FindParent
        {
            TreeNode previousNode=null;
            TreeNode parentNode=null;
            int iLoc=1;
            int jLoc=1;
            
            TreeNode findParent(TreeNode node, TreeNode findNode)
            {
                if(node==findNode)
                {
                    parentNode=previousNode;
                }
                if(node.left!=null)
                {
                    previousNode=node;
                    findParent(node.left, findNode);
                }
                if(node.right!=null)
                {
                    previousNode=node;
                    findParent(node.right, findNode);
                }
                return parentNode;
            }
        }
        pAncestor = p;
        qAncestor = q;
        pLocation = new FindLocation().findLocation(root, p); pi=pLocation[0];
        qLocation = new FindLocation().findLocation(root, q); qi=pLocation[0];
        while(pi>qi && pi>=0)
        {
            pi--; pAncestor=new FindParent().findParent(root, pAncestor);
        }
        while(pi<qi && qi>=0)
        {
            qi--; qAncestor=new FindParent().findParent(root, qAncestor);
        }
        while(pAncestor!=qAncestor && pi>=0 && qi>=0)
        {
            pi--; pAncestor=new FindParent().findParent(root, pAncestor);
            qi--; qAncestor=new FindParent().findParent(root, qAncestor);
        }
        if(pi>=1 && qi>=1 && pAncestor==qAncestor)
        {
            return pAncestor;
        }
        return null;
    }    
}

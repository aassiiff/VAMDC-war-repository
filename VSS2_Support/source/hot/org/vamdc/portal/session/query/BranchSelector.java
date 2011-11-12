package org.vamdc.portal.session.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.ScopeType;
import org.richfaces.component.UITree;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

@Scope(ScopeType.SESSION)
@Name("branches")
public class BranchSelector {

	private Node menuTree;
	
	private List<Node> nodes;
	
	public class Node extends TreeNodeImpl<Node>{
		private boolean active;
		private String name;
		private String key;
		private static final long serialVersionUID = -7235491066493638125L;

		
		
		public Node(String name,String keyword){
			super();
			this.setData(this);
			this.name=name;
			this.active=true;
			//this.key=keyword;
		}
		
		public boolean isActive(){
			return ((active && key!=null) || hasActiveChild());
		}
		
		/**
		 * 
		 * @return true if node has active child nodes
		 */
		public boolean hasActiveChild(){
			boolean yes = false;
			Iterator<Entry<Object,TreeNode<Node>>> children = this.getChildren();
			while (children.hasNext()){
				Node myChild = (Node) children.next().getValue();
				if (myChild.isActive())
					yes=true;
			}
			return yes;
		}
		
		public String getName(){
			return this.name;
		}
		
		/**
		 * Process local click event for this item
		 */
		public void clicked(){
			this.active=!this.isActive();
			if (this.active){
				enable();
				if (this.key==null){//Enable children for grouping elements
					enableChildren();
				}
			}else{
				disableChildren();
			}
		}
		
		/**
		 * Enable element and all parents
		 */
		private void enable(){
			this.active=true;
			if (this.getParent()!=null && this.getParent().getData()!=null)
				this.getParent().getData().enable();
		}
		
		/**
		 * Disable element and all children
		 */
		private void disable(){
			this.active=false;
			disableChildren();
		}
		
		/**
		 * enable children non-recursively
		 */
		private void enableChildren(){
			Iterator<Entry<Object,TreeNode<Node>>> children = this.getChildren();
			while (children.hasNext()){
				Node myChild = (Node) children.next().getValue();
				myChild.enable();
			}
		}
		
		/**
		 * Recursively disable children
		 */
		private void disableChildren(){
			Iterator<Entry<Object,TreeNode<Node>>> children = this.getChildren();
			while (children.hasNext()){
				Node myChild = (Node) children.next().getValue();
				myChild.disable();
			}
		}
		
	}
	
	private Node getNode(String name,String keyword){
		Node newNode = new Node(name,keyword);
		nodes.add(newNode);
		return newNode;
	}
	
	public BranchSelector(){
		
		nodes=new ArrayList<Node>();
		menuTree = getNode("XSAMS",null);
		
		//Build a tree. Maybe later use XML for it?
		Node node = null,child=null;
		node = getNode("Atoms","Atoms");
		
		child=getNode("States","AtomStates");
		node.addChild(0, child);
		
		child=node;
		node = getNode("Species",null);
		
		node.addChild(0, child);
		menuTree.addChild(0, node);
		
		child=getNode("States","MoleculeStates");
		child.addChild(0, getNode("Quantum Numbers","MoleculeQuantumNumbers"));
		node = getNode("Molecules","Molecules");
		node.addChild(0, child);
		menuTree.getChild(0).addChild(1, node);
		
		menuTree.getChild(0).addChild(2, getNode("Particles","Particles"));
		menuTree.getChild(0).addChild(3, getNode("Solids","Solids"));
		
		node = getNode("Processes",null);
		menuTree.addChild(1, node);
		
		//node.addChild(0, getNode("CrossSections",Requestable.,Meta.processes));
		node.addChild(1, getNode("Transitions","RadiativeTransitions"));
		node.addChild(2, getNode("Collisions","Collisions"));
		
	}
	
	public void selectAll(){
		for(Node node:nodes){
			node.active=true;
		}
	}
	public void selectNone(){
		for(Node node:nodes){
			node.active=false;
		}
	}
	
	public TreeNodeImpl<Node> getTree(){
		return menuTree;
	}
	
	public boolean expanded(UITree tree) {
		 return true;
	}
	
	/**
	 * Get a string representing the "requestables" section, defining requested branches.
	 * @return
	 */
	public String getQuery(){
		boolean all=true;
		Set<String> reqs = new TreeSet<String>();
		//Collect keywords from enabled nodes:
		for (Node node:nodes){
			if (!node.isActive())
				all=false;
			if (node.key!=null && node.isActive() && !node.hasActiveChild()){
				reqs.add(node.key);
			}
		}
		if (all||reqs.isEmpty())
			return "ALL";
		else{
			//Normalize set a bit
			//if (reqs.contains(Requestable.Atoms)&&reqs.c)
			String result = "";
			for (String key:reqs){
				result+=key +",";
			}
			return result.substring(0,result.length()-1);
		}
	}
}



public class Block 
{
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + name;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Block))
			return false;
		Block other = (Block) obj;
		if (name != other.name)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Integer.toString(name);
	}

	private Block(int i) 
	{
		id = i;
		name = i+1;
	}
	final int id;
	final int name;
	
	static Block fromId(int id)
	{
		return new Block(id);
	}
	
	static Block fromName(int id)
	{
		return new Block(id-1);
	}

}

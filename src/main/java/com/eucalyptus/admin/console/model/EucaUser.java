package com.eucalyptus.admin.console.model;


public class EucaUser {

	private String account;
    /**
     * Path to the user. For more information about paths, see <a
     * href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     * target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     * Identity and Access Management</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 512<br/>
     * <b>Pattern: </b>(\u002F)|(\u002F[\u0021-\u007F]+\u002F)<br/>
     */
    private String path;

    /**
     * The name identifying the user.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 64<br/>
     * <b>Pattern: </b>[\w+=,.@-]*<br/>
     */
    private String userName;

    /**
     * The stable and unique string identifying the user. For more
     * information about IDs, see <a
     * href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     * target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     * Identity and Access Management</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>16 - 32<br/>
     * <b>Pattern: </b>[\w]*<br/>
     */
    private String userId;

    /**
     * The Amazon Resource Name (ARN) specifying the user. For more
     * information about ARNs and how to use them in policies, see <a
     * href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     * target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     * Identity and Access Management</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>20 - 2048<br/>
     */
    private String arn;
    
    private java.util.Collection<EucaGroup> groups;

    /**
     * The date when the user was created.
     */
    private java.util.Date createDate;

    /**
     * Default constructor for a new User object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public EucaUser() {}
    
    
    /**
     * Constructs a new User object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param path Path to the user. For more information about paths, see <a
     * href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     * target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     * Identity and Access Management</i>.
     * @param userName The name identifying the user.
     * @param userId The stable and unique string identifying the user. For
     * more information about IDs, see <a
     * href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     * target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     * Identity and Access Management</i>.
     * @param arn The Amazon Resource Name (ARN) specifying the user. For
     * more information about ARNs and how to use them in policies, see <a
     * href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     * target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     * Identity and Access Management</i>.
     * @param createDate The date when the user was created.
     */
    public EucaUser(String path, String userName, String userId, String arn, java.util.Date createDate) {
        setPath(path);
        setUserName(userName);
        setUserId(userId);
        setArn(arn);
        setCreateDate(createDate);
    }
    
    /**
     * 
     * @return account name of the user
     */
    public String getAccount() {
        return account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    
    /**
     * return the groups which the user belong
     * @return
     */
    public java.util.Collection<EucaGroup> getGroups()
    {
    	if (this.groups == null ) {
    		this.groups = new java.util.ArrayList<EucaGroup>();
    	}
    	return this.groups;
    }
    
    public void setGroups(java.util.Collection<EucaGroup> groups)
    {
    	if (groups == null) {
            this.groups = null;
            return;
        }
        java.util.ArrayList<EucaGroup> groupsCopy = new java.util.ArrayList<EucaGroup>(groups.size());
        groupsCopy.addAll(groups);
        this.groups = groupsCopy;
    }
    /**
     * Path to the user. For more information about paths, see <a
     * href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     * target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     * Identity and Access Management</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 512<br/>
     * <b>Pattern: </b>(\u002F)|(\u002F[\u0021-\u007F]+\u002F)<br/>
     *
     * @return Path to the user. For more information about paths, see <a
     *         href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     *         target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     *         Identity and Access Management</i>.
     */
    public String getPath() {
        return path;
    }
    
    
    /**
     * Path to the user. For more information about paths, see <a
     * href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     * target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     * Identity and Access Management</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 512<br/>
     * <b>Pattern: </b>(\u002F)|(\u002F[\u0021-\u007F]+\u002F)<br/>
     *
     * @param path Path to the user. For more information about paths, see <a
     *         href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     *         target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     *         Identity and Access Management</i>.
     */
    public void setPath(String path) {
        this.path = path;
    }
    
    /**
     * Path to the user. For more information about paths, see <a
     * href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     * target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     * Identity and Access Management</i>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 512<br/>
     * <b>Pattern: </b>(\u002F)|(\u002F[\u0021-\u007F]+\u002F)<br/>
     *
     * @param path Path to the user. For more information about paths, see <a
     *         href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     *         target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     *         Identity and Access Management</i>.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together.
     */
    public EucaUser withPath(String path) {
        this.path = path;
        return this;
    }
    
    
    /**
     * The name identifying the user.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 64<br/>
     * <b>Pattern: </b>[\w+=,.@-]*<br/>
     *
     * @return The name identifying the user.
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * The name identifying the user.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 64<br/>
     * <b>Pattern: </b>[\w+=,.@-]*<br/>
     *
     * @param userName The name identifying the user.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * The name identifying the user.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 64<br/>
     * <b>Pattern: </b>[\w+=,.@-]*<br/>
     *
     * @param userName The name identifying the user.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together.
     */
    public EucaUser withUserName(String userName) {
        this.userName = userName;
        return this;
    }
    
    
    /**
     * The stable and unique string identifying the user. For more
     * information about IDs, see <a
     * href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     * target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     * Identity and Access Management</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>16 - 32<br/>
     * <b>Pattern: </b>[\w]*<br/>
     *
     * @return The stable and unique string identifying the user. For more
     *         information about IDs, see <a
     *         href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     *         target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     *         Identity and Access Management</i>.
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * The stable and unique string identifying the user. For more
     * information about IDs, see <a
     * href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     * target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     * Identity and Access Management</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>16 - 32<br/>
     * <b>Pattern: </b>[\w]*<br/>
     *
     * @param userId The stable and unique string identifying the user. For more
     *         information about IDs, see <a
     *         href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     *         target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     *         Identity and Access Management</i>.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * The stable and unique string identifying the user. For more
     * information about IDs, see <a
     * href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     * target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     * Identity and Access Management</i>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>16 - 32<br/>
     * <b>Pattern: </b>[\w]*<br/>
     *
     * @param userId The stable and unique string identifying the user. For more
     *         information about IDs, see <a
     *         href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     *         target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     *         Identity and Access Management</i>.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together.
     */
    public EucaUser withUserId(String userId) {
        this.userId = userId;
        return this;
    }
    
    
    /**
     * The Amazon Resource Name (ARN) specifying the user. For more
     * information about ARNs and how to use them in policies, see <a
     * href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     * target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     * Identity and Access Management</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>20 - 2048<br/>
     *
     * @return The Amazon Resource Name (ARN) specifying the user. For more
     *         information about ARNs and how to use them in policies, see <a
     *         href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     *         target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     *         Identity and Access Management</i>.
     */
    public String getArn() {
        return arn;
    }
    
    /**
     * The Amazon Resource Name (ARN) specifying the user. For more
     * information about ARNs and how to use them in policies, see <a
     * href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     * target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     * Identity and Access Management</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>20 - 2048<br/>
     *
     * @param arn The Amazon Resource Name (ARN) specifying the user. For more
     *         information about ARNs and how to use them in policies, see <a
     *         href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     *         target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     *         Identity and Access Management</i>.
     */
    public void setArn(String arn) {
        this.arn = arn;
    }
    
    /**
     * The Amazon Resource Name (ARN) specifying the user. For more
     * information about ARNs and how to use them in policies, see <a
     * href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     * target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     * Identity and Access Management</i>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>20 - 2048<br/>
     *
     * @param arn The Amazon Resource Name (ARN) specifying the user. For more
     *         information about ARNs and how to use them in policies, see <a
     *         href="http://docs.amazonwebservices.com/IAM/latest/UserGuide/index.html?Using_Identifiers.html"
     *         target="_blank">Identifiers for IAM Entities</a> in <i>Using AWS
     *         Identity and Access Management</i>.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together.
     */
    public EucaUser withArn(String arn) {
        this.arn = arn;
        return this;
    }
    
    
    /**
     * The date when the user was created.
     *
     * @return The date when the user was created.
     */
    public java.util.Date getCreateDate() {
        return createDate;
    }
    
    /**
     * The date when the user was created.
     *
     * @param createDate The date when the user was created.
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }
    
    /**
     * The date when the user was created.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param createDate The date when the user was created.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together.
     */
    public EucaUser withCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
        return this;
    }
    
    
    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPath() != null) sb.append("Path: " + getPath() + ",");
        if (getUserName() != null) sb.append("UserName: " + getUserName() + ",");
        if (getUserId() != null) sb.append("UserId: " + getUserId() + ",");
        if (getArn() != null) sb.append("Arn: " + getArn() + ",");
        if (getCreateDate() != null) sb.append("CreateDate: " + getCreateDate() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((getPath() == null) ? 0 : getPath().hashCode()); 
        hashCode = prime * hashCode + ((getUserName() == null) ? 0 : getUserName().hashCode()); 
        hashCode = prime * hashCode + ((getUserId() == null) ? 0 : getUserId().hashCode()); 
        hashCode = prime * hashCode + ((getArn() == null) ? 0 : getArn().hashCode()); 
        hashCode = prime * hashCode + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof EucaUser == false) return false;
        EucaUser other = (EucaUser)obj;
        
        if (other.getPath() == null ^ this.getPath() == null) return false;
        if (other.getPath() != null && other.getPath().equals(this.getPath()) == false) return false; 
        if (other.getUserName() == null ^ this.getUserName() == null) return false;
        if (other.getUserName() != null && other.getUserName().equals(this.getUserName()) == false) return false; 
        if (other.getUserId() == null ^ this.getUserId() == null) return false;
        if (other.getUserId() != null && other.getUserId().equals(this.getUserId()) == false) return false; 
        if (other.getArn() == null ^ this.getArn() == null) return false;
        if (other.getArn() != null && other.getArn().equals(this.getArn()) == false) return false; 
        if (other.getCreateDate() == null ^ this.getCreateDate() == null) return false;
        if (other.getCreateDate() != null && other.getCreateDate().equals(this.getCreateDate()) == false) return false; 
        return true;
    }
    
}
    
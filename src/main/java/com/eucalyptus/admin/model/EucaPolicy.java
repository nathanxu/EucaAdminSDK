package com.eucalyptus.admin.model;
import java.io.Serializable;

public class EucaPolicy {

    /**
     * The user the policy is associated with.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 128<br/>
     * <b>Pattern: </b>[\w+=,.@-]*<br/>
     */
    private String userName;
    
    /**
     * owner account of this po
     */
    private String account;
    
    /**
     * group owned this policy
     */
    private String group;

    /**
     * The name of the policy.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 128<br/>
     * <b>Pattern: </b>[\w+=,.@-]*<br/>
     */
    private String policyName;

    /**
     * The policy document.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 131072<br/>
     * <b>Pattern: </b>[\u0009\u000A\u000D\u0020-\u00FF]+<br/>
     */
    private String policyDocument;

    /**
     * The user the policy is associated with.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 128<br/>
     * <b>Pattern: </b>[\w+=,.@-]*<br/>
     *
     * @return The user the policy is associated with.
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * The user the policy is associated with.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 128<br/>
     * <b>Pattern: </b>[\w+=,.@-]*<br/>
     *
     * @param userName The user the policy is associated with.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * The user the policy is associated with.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 128<br/>
     * <b>Pattern: </b>[\w+=,.@-]*<br/>
     *
     * @param userName The user the policy is associated with.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together.
     */
    public EucaPolicy withUserName(String userName) {
        this.userName = userName;
        return this;
    }
    
    public String getAccountName() {
        return account;
    }
    
    public void setAccountName(String account) {
        this.account = account;
    }
    
    public String getGroupName() {
        return group;
    }
    
    public void setGroupName(String group) {
        this.group = group;
    }
    
    /**
     * The name of the policy.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 128<br/>
     * <b>Pattern: </b>[\w+=,.@-]*<br/>
     *
     * @return The name of the policy.
     */
    public String getPolicyName() {
        return policyName;
    }
    
    /**
     * The name of the policy.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 128<br/>
     * <b>Pattern: </b>[\w+=,.@-]*<br/>
     *
     * @param policyName The name of the policy.
     */
    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }
    
    /**
     * The name of the policy.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 128<br/>
     * <b>Pattern: </b>[\w+=,.@-]*<br/>
     *
     * @param policyName The name of the policy.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together.
     */
    public EucaPolicy withPolicyName(String policyName) {
        this.policyName = policyName;
        return this;
    }
    
    
    /**
     * The policy document.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 131072<br/>
     * <b>Pattern: </b>[\u0009\u000A\u000D\u0020-\u00FF]+<br/>
     *
     * @return The policy document.
     */
    public String getPolicyDocument() {
        return policyDocument;
    }
    
    /**
     * The policy document.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 131072<br/>
     * <b>Pattern: </b>[\u0009\u000A\u000D\u0020-\u00FF]+<br/>
     *
     * @param policyDocument The policy document.
     */
    public void setPolicyDocument(String policyDocument) {
        this.policyDocument = policyDocument;
    }
    
    /**
     * The policy document.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 131072<br/>
     * <b>Pattern: </b>[\u0009\u000A\u000D\u0020-\u00FF]+<br/>
     *
     * @param policyDocument The policy document.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together.
     */
    public EucaPolicy withPolicyDocument(String policyDocument) {
        this.policyDocument = policyDocument;
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
        if (getUserName() != null) sb.append("UserName: " + getUserName() + ",");
        if (getPolicyName() != null) sb.append("PolicyName: " + getPolicyName() + ",");
        if (getPolicyDocument() != null) sb.append("PolicyDocument: " + getPolicyDocument() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((getUserName() == null) ? 0 : getUserName().hashCode()); 
        hashCode = prime * hashCode + ((getPolicyName() == null) ? 0 : getPolicyName().hashCode()); 
        hashCode = prime * hashCode + ((getPolicyDocument() == null) ? 0 : getPolicyDocument().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof EucaPolicy == false) return false;
        EucaPolicy other = (EucaPolicy)obj;
        
        if (other.getUserName() == null ^ this.getUserName() == null) return false;
        if (other.getUserName() != null && other.getUserName().equals(this.getUserName()) == false) return false; 
        if (other.getPolicyName() == null ^ this.getPolicyName() == null) return false;
        if (other.getPolicyName() != null && other.getPolicyName().equals(this.getPolicyName()) == false) return false; 
        if (other.getPolicyDocument() == null ^ this.getPolicyDocument() == null) return false;
        if (other.getPolicyDocument() != null && other.getPolicyDocument().equals(this.getPolicyDocument()) == false) return false; 
        return true;
    }
    
}
    
/**
 * GetCustomViewById.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetCustomViewById  implements java.io.Serializable {
    private java.lang.String authKey;

    private java.lang.String authKeyAdmin;

    private int staffId;

    private int viewId;

    private int startPageIndex;

    private int pageSize;

    private java.lang.String sortColumn;

    private java.lang.String sortDirection;

    private boolean allRecords;

    public GetCustomViewById() {
    }

    public GetCustomViewById(
           java.lang.String authKey,
           java.lang.String authKeyAdmin,
           int staffId,
           int viewId,
           int startPageIndex,
           int pageSize,
           java.lang.String sortColumn,
           java.lang.String sortDirection,
           boolean allRecords) {
           this.authKey = authKey;
           this.authKeyAdmin = authKeyAdmin;
           this.staffId = staffId;
           this.viewId = viewId;
           this.startPageIndex = startPageIndex;
           this.pageSize = pageSize;
           this.sortColumn = sortColumn;
           this.sortDirection = sortDirection;
           this.allRecords = allRecords;
    }


    /**
     * Gets the authKey value for this GetCustomViewById.
     * 
     * @return authKey
     */
    public java.lang.String getAuthKey() {
        return authKey;
    }


    /**
     * Sets the authKey value for this GetCustomViewById.
     * 
     * @param authKey
     */
    public void setAuthKey(java.lang.String authKey) {
        this.authKey = authKey;
    }


    /**
     * Gets the authKeyAdmin value for this GetCustomViewById.
     * 
     * @return authKeyAdmin
     */
    public java.lang.String getAuthKeyAdmin() {
        return authKeyAdmin;
    }


    /**
     * Sets the authKeyAdmin value for this GetCustomViewById.
     * 
     * @param authKeyAdmin
     */
    public void setAuthKeyAdmin(java.lang.String authKeyAdmin) {
        this.authKeyAdmin = authKeyAdmin;
    }


    /**
     * Gets the staffId value for this GetCustomViewById.
     * 
     * @return staffId
     */
    public int getStaffId() {
        return staffId;
    }


    /**
     * Sets the staffId value for this GetCustomViewById.
     * 
     * @param staffId
     */
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }


    /**
     * Gets the viewId value for this GetCustomViewById.
     * 
     * @return viewId
     */
    public int getViewId() {
        return viewId;
    }


    /**
     * Sets the viewId value for this GetCustomViewById.
     * 
     * @param viewId
     */
    public void setViewId(int viewId) {
        this.viewId = viewId;
    }


    /**
     * Gets the startPageIndex value for this GetCustomViewById.
     * 
     * @return startPageIndex
     */
    public int getStartPageIndex() {
        return startPageIndex;
    }


    /**
     * Sets the startPageIndex value for this GetCustomViewById.
     * 
     * @param startPageIndex
     */
    public void setStartPageIndex(int startPageIndex) {
        this.startPageIndex = startPageIndex;
    }


    /**
     * Gets the pageSize value for this GetCustomViewById.
     * 
     * @return pageSize
     */
    public int getPageSize() {
        return pageSize;
    }


    /**
     * Sets the pageSize value for this GetCustomViewById.
     * 
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * Gets the sortColumn value for this GetCustomViewById.
     * 
     * @return sortColumn
     */
    public java.lang.String getSortColumn() {
        return sortColumn;
    }


    /**
     * Sets the sortColumn value for this GetCustomViewById.
     * 
     * @param sortColumn
     */
    public void setSortColumn(java.lang.String sortColumn) {
        this.sortColumn = sortColumn;
    }


    /**
     * Gets the sortDirection value for this GetCustomViewById.
     * 
     * @return sortDirection
     */
    public java.lang.String getSortDirection() {
        return sortDirection;
    }


    /**
     * Sets the sortDirection value for this GetCustomViewById.
     * 
     * @param sortDirection
     */
    public void setSortDirection(java.lang.String sortDirection) {
        this.sortDirection = sortDirection;
    }


    /**
     * Gets the allRecords value for this GetCustomViewById.
     * 
     * @return allRecords
     */
    public boolean isAllRecords() {
        return allRecords;
    }


    /**
     * Sets the allRecords value for this GetCustomViewById.
     * 
     * @param allRecords
     */
    public void setAllRecords(boolean allRecords) {
        this.allRecords = allRecords;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetCustomViewById)) return false;
        GetCustomViewById other = (GetCustomViewById) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.authKey==null && other.getAuthKey()==null) || 
             (this.authKey!=null &&
              this.authKey.equals(other.getAuthKey()))) &&
            ((this.authKeyAdmin==null && other.getAuthKeyAdmin()==null) || 
             (this.authKeyAdmin!=null &&
              this.authKeyAdmin.equals(other.getAuthKeyAdmin()))) &&
            this.staffId == other.getStaffId() &&
            this.viewId == other.getViewId() &&
            this.startPageIndex == other.getStartPageIndex() &&
            this.pageSize == other.getPageSize() &&
            ((this.sortColumn==null && other.getSortColumn()==null) || 
             (this.sortColumn!=null &&
              this.sortColumn.equals(other.getSortColumn()))) &&
            ((this.sortDirection==null && other.getSortDirection()==null) || 
             (this.sortDirection!=null &&
              this.sortDirection.equals(other.getSortDirection()))) &&
            this.allRecords == other.isAllRecords();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAuthKey() != null) {
            _hashCode += getAuthKey().hashCode();
        }
        if (getAuthKeyAdmin() != null) {
            _hashCode += getAuthKeyAdmin().hashCode();
        }
        _hashCode += getStaffId();
        _hashCode += getViewId();
        _hashCode += getStartPageIndex();
        _hashCode += getPageSize();
        if (getSortColumn() != null) {
            _hashCode += getSortColumn().hashCode();
        }
        if (getSortDirection() != null) {
            _hashCode += getSortDirection().hashCode();
        }
        _hashCode += (isAllRecords() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetCustomViewById.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetCustomViewById"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "authKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authKeyAdmin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "authKeyAdmin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("staffId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "staffId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("viewId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "viewId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startPageIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "startPageIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "pageSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sortColumn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "sortColumn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sortDirection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "sortDirection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allRecords");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "allRecords"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

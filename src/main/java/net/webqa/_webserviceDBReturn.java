/**
 * _webserviceDBReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class _webserviceDBReturn  implements java.io.Serializable {
    private net.webqa._webserviceDBReturnDataSource dataSource;

    private int itemCount;

    private java.lang.String sortDirection;

    public _webserviceDBReturn() {
    }

    public _webserviceDBReturn(
           net.webqa._webserviceDBReturnDataSource dataSource,
           int itemCount,
           java.lang.String sortDirection) {
           this.dataSource = dataSource;
           this.itemCount = itemCount;
           this.sortDirection = sortDirection;
    }


    /**
     * Gets the dataSource value for this _webserviceDBReturn.
     * 
     * @return dataSource
     */
    public net.webqa._webserviceDBReturnDataSource getDataSource() {
        return dataSource;
    }


    /**
     * Sets the dataSource value for this _webserviceDBReturn.
     * 
     * @param dataSource
     */
    public void setDataSource(net.webqa._webserviceDBReturnDataSource dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * Gets the itemCount value for this _webserviceDBReturn.
     * 
     * @return itemCount
     */
    public int getItemCount() {
        return itemCount;
    }


    /**
     * Sets the itemCount value for this _webserviceDBReturn.
     * 
     * @param itemCount
     */
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }


    /**
     * Gets the sortDirection value for this _webserviceDBReturn.
     * 
     * @return sortDirection
     */
    public java.lang.String getSortDirection() {
        return sortDirection;
    }


    /**
     * Sets the sortDirection value for this _webserviceDBReturn.
     * 
     * @param sortDirection
     */
    public void setSortDirection(java.lang.String sortDirection) {
        this.sortDirection = sortDirection;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof _webserviceDBReturn)) return false;
        _webserviceDBReturn other = (_webserviceDBReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataSource==null && other.getDataSource()==null) || 
             (this.dataSource!=null &&
              this.dataSource.equals(other.getDataSource()))) &&
            this.itemCount == other.getItemCount() &&
            ((this.sortDirection==null && other.getSortDirection()==null) || 
             (this.sortDirection!=null &&
              this.sortDirection.equals(other.getSortDirection())));
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
        if (getDataSource() != null) {
            _hashCode += getDataSource().hashCode();
        }
        _hashCode += getItemCount();
        if (getSortDirection() != null) {
            _hashCode += getSortDirection().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(_webserviceDBReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", "_webserviceDBReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataSource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "dataSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">_webserviceDBReturn>dataSource"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itemCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "itemCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sortDirection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "sortDirection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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

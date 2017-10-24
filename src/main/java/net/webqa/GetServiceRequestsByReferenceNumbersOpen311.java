/**
 * GetServiceRequestsByReferenceNumbersOpen311.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetServiceRequestsByReferenceNumbersOpen311  implements java.io.Serializable {
    private java.lang.String authKey;

    private java.lang.String referenceNumbers;

    public GetServiceRequestsByReferenceNumbersOpen311() {
    }

    public GetServiceRequestsByReferenceNumbersOpen311(
           java.lang.String authKey,
           java.lang.String referenceNumbers) {
           this.authKey = authKey;
           this.referenceNumbers = referenceNumbers;
    }


    /**
     * Gets the authKey value for this GetServiceRequestsByReferenceNumbersOpen311.
     * 
     * @return authKey
     */
    public java.lang.String getAuthKey() {
        return authKey;
    }


    /**
     * Sets the authKey value for this GetServiceRequestsByReferenceNumbersOpen311.
     * 
     * @param authKey
     */
    public void setAuthKey(java.lang.String authKey) {
        this.authKey = authKey;
    }


    /**
     * Gets the referenceNumbers value for this GetServiceRequestsByReferenceNumbersOpen311.
     * 
     * @return referenceNumbers
     */
    public java.lang.String getReferenceNumbers() {
        return referenceNumbers;
    }


    /**
     * Sets the referenceNumbers value for this GetServiceRequestsByReferenceNumbersOpen311.
     * 
     * @param referenceNumbers
     */
    public void setReferenceNumbers(java.lang.String referenceNumbers) {
        this.referenceNumbers = referenceNumbers;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceRequestsByReferenceNumbersOpen311)) return false;
        GetServiceRequestsByReferenceNumbersOpen311 other = (GetServiceRequestsByReferenceNumbersOpen311) obj;
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
            ((this.referenceNumbers==null && other.getReferenceNumbers()==null) || 
             (this.referenceNumbers!=null &&
              this.referenceNumbers.equals(other.getReferenceNumbers())));
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
        if (getReferenceNumbers() != null) {
            _hashCode += getReferenceNumbers().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetServiceRequestsByReferenceNumbersOpen311.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetServiceRequestsByReferenceNumbersOpen311"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "authKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenceNumbers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "referenceNumbers"));
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

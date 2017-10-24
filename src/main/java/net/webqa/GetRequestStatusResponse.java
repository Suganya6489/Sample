/**
 * GetRequestStatusResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetRequestStatusResponse  implements java.io.Serializable {
    private java.lang.String getRequestStatusResult;

    public GetRequestStatusResponse() {
    }

    public GetRequestStatusResponse(
           java.lang.String getRequestStatusResult) {
           this.getRequestStatusResult = getRequestStatusResult;
    }


    /**
     * Gets the getRequestStatusResult value for this GetRequestStatusResponse.
     * 
     * @return getRequestStatusResult
     */
    public java.lang.String getGetRequestStatusResult() {
        return getRequestStatusResult;
    }


    /**
     * Sets the getRequestStatusResult value for this GetRequestStatusResponse.
     * 
     * @param getRequestStatusResult
     */
    public void setGetRequestStatusResult(java.lang.String getRequestStatusResult) {
        this.getRequestStatusResult = getRequestStatusResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetRequestStatusResponse)) return false;
        GetRequestStatusResponse other = (GetRequestStatusResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getRequestStatusResult==null && other.getGetRequestStatusResult()==null) || 
             (this.getRequestStatusResult!=null &&
              this.getRequestStatusResult.equals(other.getGetRequestStatusResult())));
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
        if (getGetRequestStatusResult() != null) {
            _hashCode += getGetRequestStatusResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetRequestStatusResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetRequestStatusResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getRequestStatusResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetRequestStatusResult"));
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

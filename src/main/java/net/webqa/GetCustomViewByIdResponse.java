/**
 * GetCustomViewByIdResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetCustomViewByIdResponse  implements java.io.Serializable {
    private net.webqa._webserviceDBReturn getCustomViewByIdResult;

    public GetCustomViewByIdResponse() {
    }

    public GetCustomViewByIdResponse(
           net.webqa._webserviceDBReturn getCustomViewByIdResult) {
           this.getCustomViewByIdResult = getCustomViewByIdResult;
    }


    /**
     * Gets the getCustomViewByIdResult value for this GetCustomViewByIdResponse.
     * 
     * @return getCustomViewByIdResult
     */
    public net.webqa._webserviceDBReturn getGetCustomViewByIdResult() {
        return getCustomViewByIdResult;
    }


    /**
     * Sets the getCustomViewByIdResult value for this GetCustomViewByIdResponse.
     * 
     * @param getCustomViewByIdResult
     */
    public void setGetCustomViewByIdResult(net.webqa._webserviceDBReturn getCustomViewByIdResult) {
        this.getCustomViewByIdResult = getCustomViewByIdResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetCustomViewByIdResponse)) return false;
        GetCustomViewByIdResponse other = (GetCustomViewByIdResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getCustomViewByIdResult==null && other.getGetCustomViewByIdResult()==null) || 
             (this.getCustomViewByIdResult!=null &&
              this.getCustomViewByIdResult.equals(other.getGetCustomViewByIdResult())));
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
        if (getGetCustomViewByIdResult() != null) {
            _hashCode += getGetCustomViewByIdResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetCustomViewByIdResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetCustomViewByIdResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getCustomViewByIdResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetCustomViewByIdResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", "_webserviceDBReturn"));
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

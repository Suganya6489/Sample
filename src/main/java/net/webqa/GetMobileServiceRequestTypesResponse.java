/**
 * GetMobileServiceRequestTypesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetMobileServiceRequestTypesResponse  implements java.io.Serializable {
    private net.webqa.GetMobileServiceRequestTypesResponseGetMobileServiceRequestTypesResult getMobileServiceRequestTypesResult;

    public GetMobileServiceRequestTypesResponse() {
    }

    public GetMobileServiceRequestTypesResponse(
           net.webqa.GetMobileServiceRequestTypesResponseGetMobileServiceRequestTypesResult getMobileServiceRequestTypesResult) {
           this.getMobileServiceRequestTypesResult = getMobileServiceRequestTypesResult;
    }


    /**
     * Gets the getMobileServiceRequestTypesResult value for this GetMobileServiceRequestTypesResponse.
     * 
     * @return getMobileServiceRequestTypesResult
     */
    public net.webqa.GetMobileServiceRequestTypesResponseGetMobileServiceRequestTypesResult getGetMobileServiceRequestTypesResult() {
        return getMobileServiceRequestTypesResult;
    }


    /**
     * Sets the getMobileServiceRequestTypesResult value for this GetMobileServiceRequestTypesResponse.
     * 
     * @param getMobileServiceRequestTypesResult
     */
    public void setGetMobileServiceRequestTypesResult(net.webqa.GetMobileServiceRequestTypesResponseGetMobileServiceRequestTypesResult getMobileServiceRequestTypesResult) {
        this.getMobileServiceRequestTypesResult = getMobileServiceRequestTypesResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetMobileServiceRequestTypesResponse)) return false;
        GetMobileServiceRequestTypesResponse other = (GetMobileServiceRequestTypesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getMobileServiceRequestTypesResult==null && other.getGetMobileServiceRequestTypesResult()==null) || 
             (this.getMobileServiceRequestTypesResult!=null &&
              this.getMobileServiceRequestTypesResult.equals(other.getGetMobileServiceRequestTypesResult())));
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
        if (getGetMobileServiceRequestTypesResult() != null) {
            _hashCode += getGetMobileServiceRequestTypesResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetMobileServiceRequestTypesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetMobileServiceRequestTypesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getMobileServiceRequestTypesResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetMobileServiceRequestTypesResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">>GetMobileServiceRequestTypesResponse>GetMobileServiceRequestTypesResult"));
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

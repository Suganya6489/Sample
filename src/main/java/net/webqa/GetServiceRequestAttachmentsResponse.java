/**
 * GetServiceRequestAttachmentsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetServiceRequestAttachmentsResponse  implements java.io.Serializable {
    private net.webqa.GetServiceRequestAttachmentsResponseGetServiceRequestAttachmentsResult getServiceRequestAttachmentsResult;

    public GetServiceRequestAttachmentsResponse() {
    }

    public GetServiceRequestAttachmentsResponse(
           net.webqa.GetServiceRequestAttachmentsResponseGetServiceRequestAttachmentsResult getServiceRequestAttachmentsResult) {
           this.getServiceRequestAttachmentsResult = getServiceRequestAttachmentsResult;
    }


    /**
     * Gets the getServiceRequestAttachmentsResult value for this GetServiceRequestAttachmentsResponse.
     * 
     * @return getServiceRequestAttachmentsResult
     */
    public net.webqa.GetServiceRequestAttachmentsResponseGetServiceRequestAttachmentsResult getGetServiceRequestAttachmentsResult() {
        return getServiceRequestAttachmentsResult;
    }


    /**
     * Sets the getServiceRequestAttachmentsResult value for this GetServiceRequestAttachmentsResponse.
     * 
     * @param getServiceRequestAttachmentsResult
     */
    public void setGetServiceRequestAttachmentsResult(net.webqa.GetServiceRequestAttachmentsResponseGetServiceRequestAttachmentsResult getServiceRequestAttachmentsResult) {
        this.getServiceRequestAttachmentsResult = getServiceRequestAttachmentsResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceRequestAttachmentsResponse)) return false;
        GetServiceRequestAttachmentsResponse other = (GetServiceRequestAttachmentsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getServiceRequestAttachmentsResult==null && other.getGetServiceRequestAttachmentsResult()==null) || 
             (this.getServiceRequestAttachmentsResult!=null &&
              this.getServiceRequestAttachmentsResult.equals(other.getGetServiceRequestAttachmentsResult())));
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
        if (getGetServiceRequestAttachmentsResult() != null) {
            _hashCode += getGetServiceRequestAttachmentsResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetServiceRequestAttachmentsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetServiceRequestAttachmentsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getServiceRequestAttachmentsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetServiceRequestAttachmentsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">>GetServiceRequestAttachmentsResponse>GetServiceRequestAttachmentsResult"));
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

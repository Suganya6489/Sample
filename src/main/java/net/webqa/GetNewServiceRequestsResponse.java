/**
 * GetNewServiceRequestsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetNewServiceRequestsResponse  implements java.io.Serializable {
    private net.webqa.GetNewServiceRequestsResponseGetNewServiceRequestsResult getNewServiceRequestsResult;

    public GetNewServiceRequestsResponse() {
    }

    public GetNewServiceRequestsResponse(
           net.webqa.GetNewServiceRequestsResponseGetNewServiceRequestsResult getNewServiceRequestsResult) {
           this.getNewServiceRequestsResult = getNewServiceRequestsResult;
    }


    /**
     * Gets the getNewServiceRequestsResult value for this GetNewServiceRequestsResponse.
     * 
     * @return getNewServiceRequestsResult
     */
    public net.webqa.GetNewServiceRequestsResponseGetNewServiceRequestsResult getGetNewServiceRequestsResult() {
        return getNewServiceRequestsResult;
    }


    /**
     * Sets the getNewServiceRequestsResult value for this GetNewServiceRequestsResponse.
     * 
     * @param getNewServiceRequestsResult
     */
    public void setGetNewServiceRequestsResult(net.webqa.GetNewServiceRequestsResponseGetNewServiceRequestsResult getNewServiceRequestsResult) {
        this.getNewServiceRequestsResult = getNewServiceRequestsResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetNewServiceRequestsResponse)) return false;
        GetNewServiceRequestsResponse other = (GetNewServiceRequestsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getNewServiceRequestsResult==null && other.getGetNewServiceRequestsResult()==null) || 
             (this.getNewServiceRequestsResult!=null &&
              this.getNewServiceRequestsResult.equals(other.getGetNewServiceRequestsResult())));
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
        if (getGetNewServiceRequestsResult() != null) {
            _hashCode += getGetNewServiceRequestsResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetNewServiceRequestsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetNewServiceRequestsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getNewServiceRequestsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetNewServiceRequestsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">>GetNewServiceRequestsResponse>GetNewServiceRequestsResult"));
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

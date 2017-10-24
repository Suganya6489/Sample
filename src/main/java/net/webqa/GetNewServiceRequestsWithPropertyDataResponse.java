/**
 * GetNewServiceRequestsWithPropertyDataResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetNewServiceRequestsWithPropertyDataResponse  implements java.io.Serializable {
    private net.webqa.GetNewServiceRequestsWithPropertyDataResponseGetNewServiceRequestsWithPropertyDataResult getNewServiceRequestsWithPropertyDataResult;

    public GetNewServiceRequestsWithPropertyDataResponse() {
    }

    public GetNewServiceRequestsWithPropertyDataResponse(
           net.webqa.GetNewServiceRequestsWithPropertyDataResponseGetNewServiceRequestsWithPropertyDataResult getNewServiceRequestsWithPropertyDataResult) {
           this.getNewServiceRequestsWithPropertyDataResult = getNewServiceRequestsWithPropertyDataResult;
    }


    /**
     * Gets the getNewServiceRequestsWithPropertyDataResult value for this GetNewServiceRequestsWithPropertyDataResponse.
     * 
     * @return getNewServiceRequestsWithPropertyDataResult
     */
    public net.webqa.GetNewServiceRequestsWithPropertyDataResponseGetNewServiceRequestsWithPropertyDataResult getGetNewServiceRequestsWithPropertyDataResult() {
        return getNewServiceRequestsWithPropertyDataResult;
    }


    /**
     * Sets the getNewServiceRequestsWithPropertyDataResult value for this GetNewServiceRequestsWithPropertyDataResponse.
     * 
     * @param getNewServiceRequestsWithPropertyDataResult
     */
    public void setGetNewServiceRequestsWithPropertyDataResult(net.webqa.GetNewServiceRequestsWithPropertyDataResponseGetNewServiceRequestsWithPropertyDataResult getNewServiceRequestsWithPropertyDataResult) {
        this.getNewServiceRequestsWithPropertyDataResult = getNewServiceRequestsWithPropertyDataResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetNewServiceRequestsWithPropertyDataResponse)) return false;
        GetNewServiceRequestsWithPropertyDataResponse other = (GetNewServiceRequestsWithPropertyDataResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getNewServiceRequestsWithPropertyDataResult==null && other.getGetNewServiceRequestsWithPropertyDataResult()==null) || 
             (this.getNewServiceRequestsWithPropertyDataResult!=null &&
              this.getNewServiceRequestsWithPropertyDataResult.equals(other.getGetNewServiceRequestsWithPropertyDataResult())));
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
        if (getGetNewServiceRequestsWithPropertyDataResult() != null) {
            _hashCode += getGetNewServiceRequestsWithPropertyDataResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetNewServiceRequestsWithPropertyDataResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetNewServiceRequestsWithPropertyDataResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getNewServiceRequestsWithPropertyDataResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetNewServiceRequestsWithPropertyDataResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">>GetNewServiceRequestsWithPropertyDataResponse>GetNewServiceRequestsWithPropertyDataResult"));
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

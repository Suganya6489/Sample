/**
 * WebQAServiceRequest2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class WebQAServiceRequest2  implements java.io.Serializable {
    private int id;

    private java.lang.String name;

    private int statusId;

    private java.util.Calendar createDate;

    private java.util.Calendar updateDate;

    private java.lang.String contactEmail;

    private java.lang.String addressOne;

    private java.lang.String addressTwo;

    private java.lang.String city;

    private java.lang.String state;

    private java.lang.String zipCode;
    
/*    private int srTypeId;
    
    private java.lang.String srAddOne;

    private java.lang.String srAddTwo;

    private java.lang.String srCity;

    private java.lang.String srState;*/

    private int requestOrigin;

    private net.webqa.WebQACustomField[] customFieldData;

    public WebQAServiceRequest2() {
    }

    public WebQAServiceRequest2(
           int id,
//           int srTypeId,
           java.lang.String name,
           int statusId,
           java.util.Calendar createDate,
           java.util.Calendar updateDate,
           java.lang.String contactEmail,
           java.lang.String addressOne,
           java.lang.String addressTwo,
           java.lang.String city,
           java.lang.String state,
           java.lang.String zipCode,
           int requestOrigin,
          /* java.lang.String srAddOne,
           java.lang.String srAddTwo,
           java.lang.String srCity,
           java.lang.String srState,*/
           
           net.webqa.WebQACustomField[] customFieldData) {
           this.id = id;
           this.name = name;
           this.statusId = statusId;
           this.createDate = createDate;
           this.updateDate = updateDate;
           this.contactEmail = contactEmail;
           this.addressOne = addressOne;
           this.addressTwo = addressTwo;
           this.city = city;
           this.state = state;
           this.zipCode = zipCode;
           this.requestOrigin = requestOrigin;
           /*this.srAddOne = srAddOne;
           this.srAddTwo = srAddTwo;
           this.srCity = srCity;
           this.srState = srState;*/
           this.customFieldData = customFieldData;
    }


    /**
     * Gets the id value for this WebQAServiceRequest2.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this WebQAServiceRequest2.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the name value for this WebQAServiceRequest2.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this WebQAServiceRequest2.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the statusId value for this WebQAServiceRequest2.
     * 
     * @return statusId
     */
    public int getStatusId() {
        return statusId;
    }


    /**
     * Sets the statusId value for this WebQAServiceRequest2.
     * 
     * @param statusId
     */
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }


    /**
     * Gets the createDate value for this WebQAServiceRequest2.
     * 
     * @return createDate
     */
    public java.util.Calendar getCreateDate() {
        return createDate;
    }


    /**
     * Sets the createDate value for this WebQAServiceRequest2.
     * 
     * @param createDate
     */
    public void setCreateDate(java.util.Calendar createDate) {
        this.createDate = createDate;
    }


    /**
     * Gets the updateDate value for this WebQAServiceRequest2.
     * 
     * @return updateDate
     */
    public java.util.Calendar getUpdateDate() {
        return updateDate;
    }


    /**
     * Sets the updateDate value for this WebQAServiceRequest2.
     * 
     * @param updateDate
     */
    public void setUpdateDate(java.util.Calendar updateDate) {
        this.updateDate = updateDate;
    }


    /**
     * Gets the contactEmail value for this WebQAServiceRequest2.
     * 
     * @return contactEmail
     */
    public java.lang.String getContactEmail() {
        return contactEmail;
    }


    /**
     * Sets the contactEmail value for this WebQAServiceRequest2.
     * 
     * @param contactEmail
     */
    public void setContactEmail(java.lang.String contactEmail) {
        this.contactEmail = contactEmail;
    }


    /**
     * Gets the addressOne value for this WebQAServiceRequest2.
     * 
     * @return addressOne
     */
    public java.lang.String getAddressOne() {
        return addressOne;
    }


    /**
     * Sets the addressOne value for this WebQAServiceRequest2.
     * 
     * @param addressOne
     */
    public void setAddressOne(java.lang.String addressOne) {
        this.addressOne = addressOne;
    }


    /**
     * Gets the addressTwo value for this WebQAServiceRequest2.
     * 
     * @return addressTwo
     */
    public java.lang.String getAddressTwo() {
        return addressTwo;
    }


    /**
     * Sets the addressTwo value for this WebQAServiceRequest2.
     * 
     * @param addressTwo
     */
    public void setAddressTwo(java.lang.String addressTwo) {
        this.addressTwo = addressTwo;
    }


    /**
     * Gets the city value for this WebQAServiceRequest2.
     * 
     * @return city
     */
    public java.lang.String getCity() {
        return city;
    }


    /**
     * Sets the city value for this WebQAServiceRequest2.
     * 
     * @param city
     */
    public void setCity(java.lang.String city) {
        this.city = city;
    }


    /**
     * Gets the state value for this WebQAServiceRequest2.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this WebQAServiceRequest2.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the zipCode value for this WebQAServiceRequest2.
     * 
     * @return zipCode
     */
    public java.lang.String getZipCode() {
        return zipCode;
    }


    /**
     * Sets the zipCode value for this WebQAServiceRequest2.
     * 
     * @param zipCode
     */
    public void setZipCode(java.lang.String zipCode) {
        this.zipCode = zipCode;
    }


    /**
     * Gets the requestOrigin value for this WebQAServiceRequest2.
     * 
     * @return requestOrigin
     */
    public int getRequestOrigin() {
        return requestOrigin;
    }


    /**
     * Sets the requestOrigin value for this WebQAServiceRequest2.
     * 
     * @param requestOrigin
     */
    public void setRequestOrigin(int requestOrigin) {
        this.requestOrigin = requestOrigin;
    }


    /**
     * Gets the customFieldData value for this WebQAServiceRequest2.
     * 
     * @return customFieldData
     */
    public net.webqa.WebQACustomField[] getCustomFieldData() {
        return customFieldData;
    }


    /**
     * Sets the customFieldData value for this WebQAServiceRequest2.
     * 
     * @param customFieldData
     */
    public void setCustomFieldData(net.webqa.WebQACustomField[] customFieldData) {
        this.customFieldData = customFieldData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WebQAServiceRequest2)) return false;
        WebQAServiceRequest2 other = (WebQAServiceRequest2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            this.statusId == other.getStatusId() &&
            ((this.createDate==null && other.getCreateDate()==null) || 
             (this.createDate!=null &&
              this.createDate.equals(other.getCreateDate()))) &&
            ((this.updateDate==null && other.getUpdateDate()==null) || 
             (this.updateDate!=null &&
              this.updateDate.equals(other.getUpdateDate()))) &&
            ((this.contactEmail==null && other.getContactEmail()==null) || 
             (this.contactEmail!=null &&
              this.contactEmail.equals(other.getContactEmail()))) &&
            ((this.addressOne==null && other.getAddressOne()==null) || 
             (this.addressOne!=null &&
              this.addressOne.equals(other.getAddressOne()))) &&
            ((this.addressTwo==null && other.getAddressTwo()==null) || 
             (this.addressTwo!=null &&
              this.addressTwo.equals(other.getAddressTwo()))) &&
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            ((this.zipCode==null && other.getZipCode()==null) || 
             (this.zipCode!=null &&
              this.zipCode.equals(other.getZipCode()))) &&
            this.requestOrigin == other.getRequestOrigin() &&
            ((this.customFieldData==null && other.getCustomFieldData()==null) || 
             (this.customFieldData!=null &&
              java.util.Arrays.equals(this.customFieldData, other.getCustomFieldData())));
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
        _hashCode += getId();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        _hashCode += getStatusId();
        if (getCreateDate() != null) {
            _hashCode += getCreateDate().hashCode();
        }
        if (getUpdateDate() != null) {
            _hashCode += getUpdateDate().hashCode();
        }
        if (getContactEmail() != null) {
            _hashCode += getContactEmail().hashCode();
        }
        if (getAddressOne() != null) {
            _hashCode += getAddressOne().hashCode();
        }
        if (getAddressTwo() != null) {
            _hashCode += getAddressTwo().hashCode();
        }
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        if (getZipCode() != null) {
            _hashCode += getZipCode().hashCode();
        }
        _hashCode += getRequestOrigin();
        if (getCustomFieldData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCustomFieldData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCustomFieldData(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WebQAServiceRequest2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", "WebQAServiceRequest2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "statusId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "createDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "updateDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "contactEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressOne");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "addressOne"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressTwo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "addressTwo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "city"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zipCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "zipCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestOrigin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "requestOrigin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customFieldData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "customFieldData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", "WebQACustomField"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webqa.net/", "WebQACustomField"));
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

/*	*//**
	 * @return the srTypeId
	 *//*
	public int getSrTypeId() {
		return srTypeId;
	}

	*//**
	 * @return the srAddOne
	 *//*
	public java.lang.String getSrAddOne() {
		return srAddOne;
	}

	*//**
	 * @return the srAddTwo
	 *//*
	public java.lang.String getSrAddTwo() {
		return srAddTwo;
	}

	*//**
	 * @return the srCity
	 *//*
	public java.lang.String getSrCity() {
		return srCity;
	}

	*//**
	 * @return the srState
	 *//*
	public java.lang.String getSrState() {
		return srState;
	}

	*//**
	 * @param srTypeId the srTypeId to set
	 *//*
	public void setSrTypeId(int srTypeId) {
		this.srTypeId = srTypeId;
	}

	*//**
	 * @param srAddOne the srAddOne to set
	 *//*
	public void setSrAddOne(java.lang.String srAddOne) {
		this.srAddOne = srAddOne;
	}

	*//**
	 * @param srAddTwo the srAddTwo to set
	 *//*
	public void setSrAddTwo(java.lang.String srAddTwo) {
		this.srAddTwo = srAddTwo;
	}

	*//**
	 * @param srCity the srCity to set
	 *//*
	public void setSrCity(java.lang.String srCity) {
		this.srCity = srCity;
	}

	*//**
	 * @param srState the srState to set
	 *//*
	public void setSrState(java.lang.String srState) {
		this.srState = srState;
	}*/

}

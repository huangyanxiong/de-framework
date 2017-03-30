/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.dataeye.crawler.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2017-03-30")
public class Job implements org.apache.thrift.TBase<Job, Job._Fields>, java.io.Serializable, Cloneable, Comparable<Job> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Job");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField CRON_FIELD_DESC = new org.apache.thrift.protocol.TField("cron", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField JOB_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("jobName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField JOB_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("jobId", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField URL_FIELD_DESC = new org.apache.thrift.protocol.TField("url", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField LEVEL_FIELD_DESC = new org.apache.thrift.protocol.TField("level", org.apache.thrift.protocol.TType.I32, (short)6);
  private static final org.apache.thrift.protocol.TField PLATFORM_FIELD_DESC = new org.apache.thrift.protocol.TField("platform", org.apache.thrift.protocol.TType.STRING, (short)7);
  private static final org.apache.thrift.protocol.TField ACCOUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("account", org.apache.thrift.protocol.TType.STRING, (short)8);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new JobStandardSchemeFactory());
    schemes.put(TupleScheme.class, new JobTupleSchemeFactory());
  }

  public int id; // required
  public String cron; // required
  public String jobName; // required
  public String jobId; // required
  public String url; // required
  public int level; // required
  public String platform; // required
  public String account; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    CRON((short)2, "cron"),
    JOB_NAME((short)3, "jobName"),
    JOB_ID((short)4, "jobId"),
    URL((short)5, "url"),
    LEVEL((short)6, "level"),
    PLATFORM((short)7, "platform"),
    ACCOUNT((short)8, "account");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // CRON
          return CRON;
        case 3: // JOB_NAME
          return JOB_NAME;
        case 4: // JOB_ID
          return JOB_ID;
        case 5: // URL
          return URL;
        case 6: // LEVEL
          return LEVEL;
        case 7: // PLATFORM
          return PLATFORM;
        case 8: // ACCOUNT
          return ACCOUNT;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ID_ISSET_ID = 0;
  private static final int __LEVEL_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.CRON, new org.apache.thrift.meta_data.FieldMetaData("cron", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.JOB_NAME, new org.apache.thrift.meta_data.FieldMetaData("jobName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.JOB_ID, new org.apache.thrift.meta_data.FieldMetaData("jobId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.URL, new org.apache.thrift.meta_data.FieldMetaData("url", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LEVEL, new org.apache.thrift.meta_data.FieldMetaData("level", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.PLATFORM, new org.apache.thrift.meta_data.FieldMetaData("platform", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ACCOUNT, new org.apache.thrift.meta_data.FieldMetaData("account", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Job.class, metaDataMap);
  }

  public Job() {
  }

  public Job(
    int id,
    String cron,
    String jobName,
    String jobId,
    String url,
    int level,
    String platform,
    String account)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.cron = cron;
    this.jobName = jobName;
    this.jobId = jobId;
    this.url = url;
    this.level = level;
    setLevelIsSet(true);
    this.platform = platform;
    this.account = account;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Job(Job other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    if (other.isSetCron()) {
      this.cron = other.cron;
    }
    if (other.isSetJobName()) {
      this.jobName = other.jobName;
    }
    if (other.isSetJobId()) {
      this.jobId = other.jobId;
    }
    if (other.isSetUrl()) {
      this.url = other.url;
    }
    this.level = other.level;
    if (other.isSetPlatform()) {
      this.platform = other.platform;
    }
    if (other.isSetAccount()) {
      this.account = other.account;
    }
  }

  public Job deepCopy() {
    return new Job(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    this.cron = null;
    this.jobName = null;
    this.jobId = null;
    this.url = null;
    setLevelIsSet(false);
    this.level = 0;
    this.platform = null;
    this.account = null;
  }

  public int getId() {
    return this.id;
  }

  public Job setId(int id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public String getCron() {
    return this.cron;
  }

  public Job setCron(String cron) {
    this.cron = cron;
    return this;
  }

  public void unsetCron() {
    this.cron = null;
  }

  /** Returns true if field cron is set (has been assigned a value) and false otherwise */
  public boolean isSetCron() {
    return this.cron != null;
  }

  public void setCronIsSet(boolean value) {
    if (!value) {
      this.cron = null;
    }
  }

  public String getJobName() {
    return this.jobName;
  }

  public Job setJobName(String jobName) {
    this.jobName = jobName;
    return this;
  }

  public void unsetJobName() {
    this.jobName = null;
  }

  /** Returns true if field jobName is set (has been assigned a value) and false otherwise */
  public boolean isSetJobName() {
    return this.jobName != null;
  }

  public void setJobNameIsSet(boolean value) {
    if (!value) {
      this.jobName = null;
    }
  }

  public String getJobId() {
    return this.jobId;
  }

  public Job setJobId(String jobId) {
    this.jobId = jobId;
    return this;
  }

  public void unsetJobId() {
    this.jobId = null;
  }

  /** Returns true if field jobId is set (has been assigned a value) and false otherwise */
  public boolean isSetJobId() {
    return this.jobId != null;
  }

  public void setJobIdIsSet(boolean value) {
    if (!value) {
      this.jobId = null;
    }
  }

  public String getUrl() {
    return this.url;
  }

  public Job setUrl(String url) {
    this.url = url;
    return this;
  }

  public void unsetUrl() {
    this.url = null;
  }

  /** Returns true if field url is set (has been assigned a value) and false otherwise */
  public boolean isSetUrl() {
    return this.url != null;
  }

  public void setUrlIsSet(boolean value) {
    if (!value) {
      this.url = null;
    }
  }

  public int getLevel() {
    return this.level;
  }

  public Job setLevel(int level) {
    this.level = level;
    setLevelIsSet(true);
    return this;
  }

  public void unsetLevel() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LEVEL_ISSET_ID);
  }

  /** Returns true if field level is set (has been assigned a value) and false otherwise */
  public boolean isSetLevel() {
    return EncodingUtils.testBit(__isset_bitfield, __LEVEL_ISSET_ID);
  }

  public void setLevelIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LEVEL_ISSET_ID, value);
  }

  public String getPlatform() {
    return this.platform;
  }

  public Job setPlatform(String platform) {
    this.platform = platform;
    return this;
  }

  public void unsetPlatform() {
    this.platform = null;
  }

  /** Returns true if field platform is set (has been assigned a value) and false otherwise */
  public boolean isSetPlatform() {
    return this.platform != null;
  }

  public void setPlatformIsSet(boolean value) {
    if (!value) {
      this.platform = null;
    }
  }

  public String getAccount() {
    return this.account;
  }

  public Job setAccount(String account) {
    this.account = account;
    return this;
  }

  public void unsetAccount() {
    this.account = null;
  }

  /** Returns true if field account is set (has been assigned a value) and false otherwise */
  public boolean isSetAccount() {
    return this.account != null;
  }

  public void setAccountIsSet(boolean value) {
    if (!value) {
      this.account = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Integer)value);
      }
      break;

    case CRON:
      if (value == null) {
        unsetCron();
      } else {
        setCron((String)value);
      }
      break;

    case JOB_NAME:
      if (value == null) {
        unsetJobName();
      } else {
        setJobName((String)value);
      }
      break;

    case JOB_ID:
      if (value == null) {
        unsetJobId();
      } else {
        setJobId((String)value);
      }
      break;

    case URL:
      if (value == null) {
        unsetUrl();
      } else {
        setUrl((String)value);
      }
      break;

    case LEVEL:
      if (value == null) {
        unsetLevel();
      } else {
        setLevel((Integer)value);
      }
      break;

    case PLATFORM:
      if (value == null) {
        unsetPlatform();
      } else {
        setPlatform((String)value);
      }
      break;

    case ACCOUNT:
      if (value == null) {
        unsetAccount();
      } else {
        setAccount((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case CRON:
      return getCron();

    case JOB_NAME:
      return getJobName();

    case JOB_ID:
      return getJobId();

    case URL:
      return getUrl();

    case LEVEL:
      return getLevel();

    case PLATFORM:
      return getPlatform();

    case ACCOUNT:
      return getAccount();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case CRON:
      return isSetCron();
    case JOB_NAME:
      return isSetJobName();
    case JOB_ID:
      return isSetJobId();
    case URL:
      return isSetUrl();
    case LEVEL:
      return isSetLevel();
    case PLATFORM:
      return isSetPlatform();
    case ACCOUNT:
      return isSetAccount();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Job)
      return this.equals((Job)that);
    return false;
  }

  public boolean equals(Job that) {
    if (that == null)
      return false;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_cron = true && this.isSetCron();
    boolean that_present_cron = true && that.isSetCron();
    if (this_present_cron || that_present_cron) {
      if (!(this_present_cron && that_present_cron))
        return false;
      if (!this.cron.equals(that.cron))
        return false;
    }

    boolean this_present_jobName = true && this.isSetJobName();
    boolean that_present_jobName = true && that.isSetJobName();
    if (this_present_jobName || that_present_jobName) {
      if (!(this_present_jobName && that_present_jobName))
        return false;
      if (!this.jobName.equals(that.jobName))
        return false;
    }

    boolean this_present_jobId = true && this.isSetJobId();
    boolean that_present_jobId = true && that.isSetJobId();
    if (this_present_jobId || that_present_jobId) {
      if (!(this_present_jobId && that_present_jobId))
        return false;
      if (!this.jobId.equals(that.jobId))
        return false;
    }

    boolean this_present_url = true && this.isSetUrl();
    boolean that_present_url = true && that.isSetUrl();
    if (this_present_url || that_present_url) {
      if (!(this_present_url && that_present_url))
        return false;
      if (!this.url.equals(that.url))
        return false;
    }

    boolean this_present_level = true;
    boolean that_present_level = true;
    if (this_present_level || that_present_level) {
      if (!(this_present_level && that_present_level))
        return false;
      if (this.level != that.level)
        return false;
    }

    boolean this_present_platform = true && this.isSetPlatform();
    boolean that_present_platform = true && that.isSetPlatform();
    if (this_present_platform || that_present_platform) {
      if (!(this_present_platform && that_present_platform))
        return false;
      if (!this.platform.equals(that.platform))
        return false;
    }

    boolean this_present_account = true && this.isSetAccount();
    boolean that_present_account = true && that.isSetAccount();
    if (this_present_account || that_present_account) {
      if (!(this_present_account && that_present_account))
        return false;
      if (!this.account.equals(that.account))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_id = true;
    list.add(present_id);
    if (present_id)
      list.add(id);

    boolean present_cron = true && (isSetCron());
    list.add(present_cron);
    if (present_cron)
      list.add(cron);

    boolean present_jobName = true && (isSetJobName());
    list.add(present_jobName);
    if (present_jobName)
      list.add(jobName);

    boolean present_jobId = true && (isSetJobId());
    list.add(present_jobId);
    if (present_jobId)
      list.add(jobId);

    boolean present_url = true && (isSetUrl());
    list.add(present_url);
    if (present_url)
      list.add(url);

    boolean present_level = true;
    list.add(present_level);
    if (present_level)
      list.add(level);

    boolean present_platform = true && (isSetPlatform());
    list.add(present_platform);
    if (present_platform)
      list.add(platform);

    boolean present_account = true && (isSetAccount());
    list.add(present_account);
    if (present_account)
      list.add(account);

    return list.hashCode();
  }

  @Override
  public int compareTo(Job other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCron()).compareTo(other.isSetCron());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCron()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.cron, other.cron);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetJobName()).compareTo(other.isSetJobName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetJobName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.jobName, other.jobName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetJobId()).compareTo(other.isSetJobId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetJobId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.jobId, other.jobId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUrl()).compareTo(other.isSetUrl());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUrl()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.url, other.url);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLevel()).compareTo(other.isSetLevel());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLevel()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.level, other.level);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPlatform()).compareTo(other.isSetPlatform());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPlatform()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.platform, other.platform);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAccount()).compareTo(other.isSetAccount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAccount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.account, other.account);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Job(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("cron:");
    if (this.cron == null) {
      sb.append("null");
    } else {
      sb.append(this.cron);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("jobName:");
    if (this.jobName == null) {
      sb.append("null");
    } else {
      sb.append(this.jobName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("jobId:");
    if (this.jobId == null) {
      sb.append("null");
    } else {
      sb.append(this.jobId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("url:");
    if (this.url == null) {
      sb.append("null");
    } else {
      sb.append(this.url);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("level:");
    sb.append(this.level);
    first = false;
    if (!first) sb.append(", ");
    sb.append("platform:");
    if (this.platform == null) {
      sb.append("null");
    } else {
      sb.append(this.platform);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("account:");
    if (this.account == null) {
      sb.append("null");
    } else {
      sb.append(this.account);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'id' because it's a primitive and you chose the non-beans generator.
    if (cron == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'cron' was not present! Struct: " + toString());
    }
    if (jobName == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'jobName' was not present! Struct: " + toString());
    }
    if (jobId == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'jobId' was not present! Struct: " + toString());
    }
    if (url == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'url' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'level' because it's a primitive and you chose the non-beans generator.
    if (platform == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'platform' was not present! Struct: " + toString());
    }
    if (account == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'account' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class JobStandardSchemeFactory implements SchemeFactory {
    public JobStandardScheme getScheme() {
      return new JobStandardScheme();
    }
  }

  private static class JobStandardScheme extends StandardScheme<Job> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Job struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.id = iprot.readI32();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CRON
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.cron = iprot.readString();
              struct.setCronIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // JOB_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.jobName = iprot.readString();
              struct.setJobNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // JOB_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.jobId = iprot.readString();
              struct.setJobIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // URL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.url = iprot.readString();
              struct.setUrlIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // LEVEL
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.level = iprot.readI32();
              struct.setLevelIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // PLATFORM
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.platform = iprot.readString();
              struct.setPlatformIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // ACCOUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.account = iprot.readString();
              struct.setAccountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetId()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'id' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetLevel()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'level' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Job struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(struct.id);
      oprot.writeFieldEnd();
      if (struct.cron != null) {
        oprot.writeFieldBegin(CRON_FIELD_DESC);
        oprot.writeString(struct.cron);
        oprot.writeFieldEnd();
      }
      if (struct.jobName != null) {
        oprot.writeFieldBegin(JOB_NAME_FIELD_DESC);
        oprot.writeString(struct.jobName);
        oprot.writeFieldEnd();
      }
      if (struct.jobId != null) {
        oprot.writeFieldBegin(JOB_ID_FIELD_DESC);
        oprot.writeString(struct.jobId);
        oprot.writeFieldEnd();
      }
      if (struct.url != null) {
        oprot.writeFieldBegin(URL_FIELD_DESC);
        oprot.writeString(struct.url);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(LEVEL_FIELD_DESC);
      oprot.writeI32(struct.level);
      oprot.writeFieldEnd();
      if (struct.platform != null) {
        oprot.writeFieldBegin(PLATFORM_FIELD_DESC);
        oprot.writeString(struct.platform);
        oprot.writeFieldEnd();
      }
      if (struct.account != null) {
        oprot.writeFieldBegin(ACCOUNT_FIELD_DESC);
        oprot.writeString(struct.account);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class JobTupleSchemeFactory implements SchemeFactory {
    public JobTupleScheme getScheme() {
      return new JobTupleScheme();
    }
  }

  private static class JobTupleScheme extends TupleScheme<Job> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Job struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.id);
      oprot.writeString(struct.cron);
      oprot.writeString(struct.jobName);
      oprot.writeString(struct.jobId);
      oprot.writeString(struct.url);
      oprot.writeI32(struct.level);
      oprot.writeString(struct.platform);
      oprot.writeString(struct.account);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Job struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.id = iprot.readI32();
      struct.setIdIsSet(true);
      struct.cron = iprot.readString();
      struct.setCronIsSet(true);
      struct.jobName = iprot.readString();
      struct.setJobNameIsSet(true);
      struct.jobId = iprot.readString();
      struct.setJobIdIsSet(true);
      struct.url = iprot.readString();
      struct.setUrlIsSet(true);
      struct.level = iprot.readI32();
      struct.setLevelIsSet(true);
      struct.platform = iprot.readString();
      struct.setPlatformIsSet(true);
      struct.account = iprot.readString();
      struct.setAccountIsSet(true);
    }
  }

}


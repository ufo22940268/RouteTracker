/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/ccheng/workspace/RouteTracker/src/com/hongbosb/tracker/ILocalBinder.aidl
 */
package com.hongbosb.tracker;
public interface ILocalBinder extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.hongbosb.tracker.ILocalBinder
{
private static final java.lang.String DESCRIPTOR = "com.hongbosb.tracker.ILocalBinder";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.hongbosb.tracker.ILocalBinder interface,
 * generating a proxy if needed.
 */
public static com.hongbosb.tracker.ILocalBinder asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.hongbosb.tracker.ILocalBinder))) {
return ((com.hongbosb.tracker.ILocalBinder)iin);
}
return new com.hongbosb.tracker.ILocalBinder.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getService:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getService();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.hongbosb.tracker.ILocalBinder
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public int getService() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getService, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getService = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public int getService() throws android.os.RemoteException;
}

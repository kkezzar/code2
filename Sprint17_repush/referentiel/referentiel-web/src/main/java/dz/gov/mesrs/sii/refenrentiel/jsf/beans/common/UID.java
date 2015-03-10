package dz.gov.mesrs.sii.refenrentiel.jsf.beans.common;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

/**
 * Cette classe est une copie de la classe de Sun sur package  java.rmi.server;
 * @author yselmane  on : 8 avr. 2014  14:36:41
 */
public final class UID implements Serializable {
	private static final long serialVersionUID = 1086053664494604050L;
	private static final Object lock = UID.class;
	private static long baseTime = System.currentTimeMillis();
	private static short nextCount = Short.MIN_VALUE;
	// This is sun's algorithm - don't ask me why ...
	private static final int uniqueNr = (new Object()).hashCode();
	private int unique;
	private long time;
	private short count;

	/**
	 * This is sun's algorithm - don't ask me why ...
	 */
	public UID() {
		synchronized (lock) {
			if (nextCount == Short.MAX_VALUE) {
				long newtime;
				for (;;) {
					newtime = System.currentTimeMillis();
					if (newtime - baseTime > 1000) {
						break;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException _) {
					}
				}
				baseTime = newtime;
				nextCount = Short.MIN_VALUE;
			}
			count = nextCount++;
			unique = uniqueNr;
			time = baseTime;
		}
	}

	public UID(short num) {
		unique = (int) num;
		time = 0;
		count = 0;
	}

	public int hashCode() {
		return (unique);
	}

	public boolean equals(Object obj) {
		if (obj instanceof UID) {
			UID uid = (UID) obj;
			if (this.unique == uid.unique && this.time == uid.time
					&& this.count == uid.count) {
				return (true);
			}
		}
		return (false);
	}

	public String toString() {
		return ("[UID: " + unique + "," + time + "," + count + "]");
	}

	public void write(DataOutput out) throws IOException {
		out.writeInt(unique);
		out.writeLong(time);
		out.writeShort(count);
	}

	public static UID read(DataInput in) throws IOException {
		UID id = new UID();
		id.unique = in.readInt();
		id.time = in.readLong();
		id.count = in.readShort();
		return (id);
	}

	public String getUidTimeStamp() {
		return "" + time + "" + count;
	}
	
}
package com.verywell.framework.utils.socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import com.verywell.iotp.admin.constants.CommonConstants;

/**
 * A Socket Client 也是一个Thread. Thread向后台轮训，获得SocketServer的反馈。
 * 
 */
public class SocketClientThread extends Thread {

	private static Socket RunningSocket;

	public static Socket getSocket() {
		String ip = CommonConstants.IOTP_ADDR;
		int port = Integer.valueOf(CommonConstants.IOTP_PORT);
		try {
			if (RunningSocket == null) {
				RunningSocket = new Socket(ip, port);
				return RunningSocket;
			}
			if (RunningSocket.isClosed()) {
				RunningSocket = new Socket(ip, port);
				return RunningSocket;
			}
			if (!RunningSocket.isConnected()) {
				RunningSocket.close();
				RunningSocket = new Socket(ip, port);
				return RunningSocket;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
		return RunningSocket;
	}

	// one instance

	private static String HexCode[] = { "0", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "A", "B", "C", "D", "E", "F" };

	private static SocketClientThread socketClient = null;
	// socket
	private Socket socket = null;

	private boolean desonnected = false;

	private BufferedInputStream in;

	public synchronized void setDesonnected(boolean cr) {
		desonnected = cr;
	}

	private SocketClientThread(Socket s) {
		super("SocketClient");
		socket = s;
		setDesonnected(false);
		start();
	}

	/**
	 * Create a Socket.
	 * 
	 * @param parent
	 * @param s
	 * @return
	 */
	public static synchronized SocketClientThread handle() {
		Socket s = getSocket();
		if (s == null)
		{
			return null;
		}
		if (socketClient == null)
		{
			socketClient = new SocketClientThread(s);//already start
			
		}
//		else {
//			if (socketClient.socket != null) {
//				try {
//					socketClient.socket.close();
//				} catch (Exception e) {
//					e.getMessage();
//				}
//			}
//			socketClient.socket = null;
//			socketClient = new SocketClientThread(s);
//		}
		return socketClient;
	}

	/**
	 * 发送消息。
	 * 
	 * @param s
	 */
	public String sendMessage(int[] buf) {
		try {
			OutputStream out = socket.getOutputStream();
			// int[] buf = { 0xfe, 0xe7, 0xe1, 0x03, 0x03, 0x02, 0x01, 0x00,
			// 0x00 };
			// byte[] bs = intToFlexBytes(0x00);
			ArrayList<byte[]> arrayList = intsToFlexBytesList(buf);
			for (int i = 0; i < arrayList.size(); i++) {
				out.write(arrayList.get(i));
			}
			out.flush();
			return "1";
		} catch (Exception e) {
			disconnect();
			return "";
		}
	}

	/**
	 * 后台接受消息。
	 */
	public void run() {
		if (socket == null)
		{
			return;
		}
		InputStream is = null;
		try {
			is = socket.getInputStream();
			in = new BufferedInputStream(is);
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e2) {
				System.err.println("Socket not closed :" + e2);
			}
			disconnect();
			return;
		}

		while (!desonnected) {
			try {
				int[] got = readInputStream(in); // in.readLine();
				if (got == null) {
					disconnect();
					break;
				}
				// call service to send infomation.
				if (got.length > 0)
				{
					try
					{
						SocketService.receiveMessage(got);
					}
					catch(Exception exp)
					{
						exp.printStackTrace();
					}
				}

			} catch (IOException e) {
				if (!desonnected) {
					disconnect();
				}
				break;
			}
		}// end of while
		try {
			is.close();
			in.close();
			// socket.close();
		} catch (Exception err) {
		}
		socket = null;
	}// end of run

	private static int[] readInputStream(BufferedInputStream _in)
			throws IOException {
		// {FE}{E7}{E1}{03}{03}{02}{01}{00}{00}
		// FE E7 E1 07 08 0A 55 F0 f0 27 10
		// FEE7E107080A55F0f02710
		// FEE7E107080A55F0F02710
		int len = _in.available();
		if (len > 0) {
			byte[] byteData = new byte[len];
			_in.read(byteData);
			int[] ias = byte22in2t(byteData);
			System.out.println(ints2Hex(ias));
			return ias;
		}
		return new int[] {};
	}

	private static int byte2int(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		return n;
	}

	private static int[] byte22in2t(byte[] bs) {
		int[] arrays = new int[bs.length];
		for (int i = 0; i < bs.length; i++) {
			arrays[i] = byte2int(bs[i]);
		}
		return arrays;
	}

	/**
	 * 字节转换成16进制
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字符串
	 */
	public static String ints2Hex(int b[]) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result = result + int2Hex(b[i]);
		}
		return result;
	}

	/**
	 * 字节转换成16进制
	 * 
	 * @param b
	 *            字节
	 * @return 16进制字符串
	 */
	private static String int2Hex(int b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return HexCode[d1] + HexCode[d2];
	}

	/**
	 * 字节转换成16进制
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字符串
	 */
	private static String bytes2Hex(byte b[]) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result = result + byte2Hex(b[i]);
		}
		return result;
	}

	/**
	 * 字节转换成16进制
	 * 
	 * @param b
	 *            字节
	 * @return 16进制字符串
	 */
	private static String byte2Hex(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return HexCode[d1] + HexCode[d2];
	}

	private synchronized void disconnect() {
		try {
			socketClient.setDesonnected(true);
			socket.close();
		} catch (Exception e) {
			System.err.println("Error closing client : " + e);
		}
		socket = null;

	}

	private static ArrayList<byte[]> intsToFlexBytesList(int[] num) {
		ArrayList<byte[]> list = new ArrayList<byte[]>();
		for (int i = 0; i < num.length; i++) {
			byte[] bs = intToFlexBytes(num[i]);
			list.add(bs);
		}
		return list;
	}

	private static byte[] intsToFlexBytes(int[] num) {
		ArrayList list = new ArrayList();
		for (int i = 0; i < num.length; i++) {
			byte[] bs = intToFlexBytes(num[i]);
			list.add(bs);
		}
		return concat(list);
	}

	private static byte[] concat(ArrayList<byte[]> arrays) {
		// Determine the length of the result array
		int totalLength = 0;
		for (int i = 0; i < arrays.size(); i++) {
			totalLength += arrays.get(i).length;
		}

		// create the result array
		byte[] result = new byte[totalLength];

		// copy the source arrays into the result array
		int currentIndex = 0;
		for (int i = 0; i < arrays.size(); i++) {
			System.arraycopy(arrays.get(i), 0, result, currentIndex,
					arrays.get(i).length);
			currentIndex += arrays.get(i).length;
		}

		return result;
	}

	private static byte[] intToFlexBytes(int n) {
		if (n == 0) {
			return new byte[] { 0x00 };
		}
		// Convert int to byte[4], via a ByteBuffer:
		byte[] bytes = new byte[4];
		ByteBuffer bb = ByteBuffer.allocateDirect(4);
		bb.asIntBuffer().put(n);
		bb.position(0);
		bb.get(bytes);

		// Leading bytes with 0:
		int i = 0;
		while (i < 4 && bytes[i] == 0)
			++i;

		// Shorten bytes array if needed:
		if (i != 0) {
			byte[] shortenedBytes = new byte[4 - i];
			for (int j = i; j < 4; ++j) {
				shortenedBytes[j - i] = bytes[j]; // System.arrayCopy not
													// needed.
			}
			bytes = shortenedBytes;
		}
		return bytes;
	}

}

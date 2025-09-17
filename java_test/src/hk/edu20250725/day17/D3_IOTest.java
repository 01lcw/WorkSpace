package hk.edu20250725.day17;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class D3_IOTest {

	public static void main(String[] args) {
//		test01();
//		test02();
//		test02_1();
//		test03();
		test04();
	}
	
	//파일을 읽고 출력하기
	public static void test01() {
		InputStream in=null;//입력파이프 선언
		OutputStream out=null;//출력파이프 선언
		
		try {
			in=new FileInputStream("C:\\Users\\hkedu\\Desktop\\HelloJava.java");
			out=new FileOutputStream("C:\\Users\\hkedu\\Desktop\\output_HelloJava.java");
			
			int i=0;//byte단위로 읽어서 데이터(실제데이터)를 저장할 변수
			while((i=in.read())!=-1) {// 읽어들일 데이터가 없으면 -1을 리턴
				System.out.print((char)i);
				out.write(i);//파일 출력하는 코드
				out.flush();//강제로 출력시키는 기능: System.out(콘솔로 출력) 다 채워질떄까지 출력X
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//마지막에 실행됐던 스트림부터 닫는다.
				if(out!=null) {
					out.close();					
				}
				if(in!=null) {
					in.close();					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//filter: 보조스트림을 이용해서 출력하기
	public static void test02() {
		String s="파일을 기록합니다.";
		String ss="파일을 문자단위로 기록합니다.";
		OutputStream out=null;//출력 파이프 준비
		OutputStreamWriter ow=null;//filter이며, 문자 인코딩 처리 기능
		BufferedWriter bw=null;//filter이며, 버퍼를 이용한 성능향상
		
		try {
			out=new FileOutputStream("C:\\Users\\hkedu\\Desktop\\output_data.java");
//			out.write(s.getBytes("utf-8"));
			
			//File바이트출력스트림 --> 문자기반출력필터 끼우고 있음
			// ==============ㅁㅁㅁㅁ
//			ow=new OutputStreamWriter(out,"utf-8");
//			ow.write(ss);
			
			//버퍼를 활용
			ow=new OutputStreamWriter(out, "utf-8");
			//버퍼를 이용해서 성능향상(많은 양이 문자를 모아서 한번에 처리)
			bw=new BufferedWriter(ow);
			bw.write(ss);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw!=null) {
					bw.close();
				}
				if(ow!=null) {
					ow.close();
				}
				if(out!=null) {
					out.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//filter: 보조스트림을 이용해서 출력하기
	public static void test02_1() {
		String s="파일을 기록합니다.";
		OutputStream out=null;//출력 파이프 준비
		DataOutputStream dos=null;//filter로 기본타입 데이터를 이진데이터로 출력
		
		try {
			out=new FileOutputStream("C:\\Users\\hkedu\\Desktop\\output_data.txt");
			dos=new DataOutputStream(out);//filter사용
//			dos.writeUTF(s);//UTF-8형식으로 인코딩된 문자열을 출력한다.
			                //문자열을 자동으로 byte단위로 나눠서 처리
//			dos.writeChars(s);//char단위로 문자여러개를 표현
			
			//filter를 미사용시
			byte[] b=s.getBytes();
			out.write(b);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(dos!=null) {
					dos.close();
				}
				if(out!=null) {
					out.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//한번에 읽을때 크기를 설정해서 읽고 쓰기
	public static void test03() {
		InputStream in=null;
		OutputStream out=null;
		
		try {
			in=new FileInputStream("C:\\Users\\hkedu\\Desktop\\test_img.png");
			out=new FileOutputStream("C:\\Users\\hkedu\\Desktop\\test_img_copy.png");
			
			//10byte 단위로 읽기
			byte [] b=new byte[6];//{0,0,0,0,0,0,0...}
			int i=0;//1byte씩 읽을때는 값이 저장-->배열로 byte를 읽으면 읽은 개수가 저장
			while((i=in.read(b))!=-1) {
				out.write(b);// [1,2,3,4,5,6,7,8,9,10]
				             // [11,12,13,14,5,6,7,8,9,10]
			                 //  ->나머지 데이터 5~10이 같이 출력된다.
				out.write(b,0,i); // [11,12,13,14,5,6,7,8,9,10]
				//b배열의 0번째부터 읽은 개수 만큼의 길이로 출력한다. -> 안정적(권장)
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(out!=null) {
					out.close();				
				}
				if(in!=null) {
					in.close();				
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//Reader와 Writer를 이용해서 키보드로 입력 받고, 출력하는 기능
	public static void test04() {
		InputStreamReader in =null;
		OutputStreamWriter out =null;
		System.out.println("입력하세요");
		
		try {
			in = new InputStreamReader(System.in);
			out = new OutputStreamWriter(System.out);
			
			char[] ch=new char[512];
			int i=0;
			while((i=in.read(ch))!=-1) {
				System.out.println("입력값: ");
				out.write(ch,0,i);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
			if(out!=null) {
				out.close();
			}
			if(in !=null) {
				in.close();
			} 
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		}
	}
}
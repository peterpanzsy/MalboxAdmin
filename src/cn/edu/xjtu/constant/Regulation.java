package cn.edu.xjtu.constant;

public enum Regulation {
		NET,REGISTER,FILE,PROCESS,ALL;
		public static Regulation getRegulation(String regulation){
			return valueOf(regulation.trim().toUpperCase());
		}
}

package com.sperkins.noderd;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

import java.util.Random;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Erase;
import org.fusesource.jansi.AnsiConsole;

public class Main {

	private static Random rando = new Random();
	public static void main(String[] args) throws ParseException {		
		
//		System.out.println(lpad("" + 5, 3));
		AnsiConsole.systemInstall();
		System.out.println(yellow("***** Node.rd *****").reset());
		
		Options options = new Options();
		Option opt = new Option("d", "download anything");
		opt.setArgName("d");
		opt.setArgs(50000);
		opt.setLongOpt("download");
		opt.setValueSeparator(' ');
		opt.setOptionalArg(true);
		options.addOption(opt);
		
		opt = new Option("d", "install anything");
		opt.setArgName("i");
		opt.setLongOpt("install");
		opt.setArgs(50000);
		opt.setValueSeparator(' ');
		opt.setOptionalArg(true);
		options.addOption(opt);
		
		opt = new Option("di", "download and install anything");
		opt.setArgName("di");
		opt.setLongOpt("download-install");
		opt.setArgs(50000);
		opt.setValueSeparator(' ');
		opt.setOptionalArg(true);
		options.addOption(opt);
		
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse( options, args);
		if(cmd.hasOption("d")) {
			download(cmd);
		}
		if(cmd.hasOption("i")) {
			install(cmd);
		}
		if(cmd.hasOption("di")) {
			String[] values = cmd.getOptionValues("di");
			for(String value: values) {
				download(value);	
			}
			for(String value: values) {
				install(value);	
			}
		}
		
		AnsiConsole.systemUninstall();
	}

	private static void download(CommandLine cmd) {
		String[] values = cmd.getOptionValues("d");
		for(String value: values) {
			download(value);	
		}
	}
	
	private static void download(String name) {
		System.out.println("Downloading " + green(name));
		System.out.print("[[");
		for(int x = 1; x <= 100; x++) {
			System.out.print(ansi().cursorLeft( x > 1 ? 6 : 0).eraseLine(Erase.FORWARD));
			System.out.print(green("="));
			System.out.print("] " + lpad("" + x, 3) + "%");
			try {
				Thread.sleep(1 + rando.nextInt(250));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int kb = rando.nextInt(500) + rando.nextInt(10000);
		System.out.print(ansi().cursorLeft(5).eraseLine(Erase.FORWARD).reset().a(yellow(kb + "K")).reset().a(" | Done"));
		System.out.println();
	}

	private static void install(CommandLine cmd) {
		String[] values = cmd.getOptionValues("i");
		for(String value: values) {
			install(value);	
		}
	}
	
	private static void install(String name) {
		System.out.println("Installing " + green(name));
		System.out.print("[[");
		for(int x = 1; x <= 100; x++) {
			System.out.print(ansi().cursorLeft( x > 1 ? 6 : 0).eraseLine(Erase.FORWARD));
			System.out.print(green("="));
			System.out.print("] " + lpad("" + x, 3) + "%");
			try {
				Thread.sleep(1 + rando.nextInt(100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.print(ansi().cursorLeft(5).eraseLine(Erase.FORWARD).reset().a("Done"));
		System.out.println();
	}
	
	private static Ansi yellow(String s) {
		return ansi().fg(YELLOW).a(s);
	}
	
	private static Ansi red(String s) {
		return ansi().fg(RED).a(s);
	}
	
	private static Ansi green(String s) {
		return ansi().fg(GREEN).a(s);
	}
	
	private static String lpad(String str, int len) {
		for(int x = 0; x <= len - str.length(); x++) {
			str = " " + str;
		}
		return str;
	}
	
}

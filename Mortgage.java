/*
	Welcome to your personal mortgage calculator
	Author: Jason Tan 
	Date: February 10th, 2018
	Please change lines 18-21 to fit your mortgage calculations.
	Please ensure that you have the Dollar.java file to run this program. 
	Once you are done editing the program, press the green circle with the white triangle above
*/

import java.io.IOException;
public class Mortgage 
{
	public static void main (String[]args)
	{
/*
	Please Edit The Following:
 */
		double principal = 500000.00;//how much you borrow (or have remaining)
		double interestRate = 0.0425/12.0; //DO NOT CONFUSE THIS WITH APR. This is the interest rate. (rate percentage is expressed as a decimal) and then divided by 12; ie: 4.25% would be expressed as 0.0425/12.0
		double myMonthlyPayment= 5000.00; //how much you are willing to pay each month onwards
		double monthlyInsuranceAndTax= 1000.00;//monthly cost of insurance, property tax, and other fees (including HOA fees if you pay them). Divide any annual fees by 12
/*
	No More Editing Beyond This Point!!!
*/
		try
		{
			printInfo(principal,interestRate, myMonthlyPayment, monthlyInsuranceAndTax);
		}
		catch (IOException ex){}
	}
	public static double p (int month, double principal, double rate, double myMonthlyPayment, double monthlyInsuranceAndTax) throws IOException
	{
		
		double c= myMonthlyPayment-monthlyInsuranceAndTax; //how much of your payment goes to paying for the loan
		double answer= 0;//returning variable
		try
		{
			if(month<0)
			{
				throw new IOException();
			}
			else if (month==0)//this is the base case for the recursion
			{
				answer=principal;
			}
			else // the amount owe is last month's balance, add interest, and THEN AFTERWARDS subtract the payment
			{
				answer= (((rate+1)*p(month-1, principal, rate, myMonthlyPayment, monthlyInsuranceAndTax))-c);
			}
		}
		catch(IOException ex){} // just need this to make the code work
		return answer;
	}
	public static double r (int month, double principal, double rate, double myMonthlyPayment, double monthlyInsuranceAndTax) throws IOException
	{	
		return (p(month-1,principal,rate, myMonthlyPayment, monthlyInsuranceAndTax)*rate);//previous principal*rate;
	}
	public static void printInfo( double principal, double rate, double myMonthlyPayment, double monthlyInsuranceAndTax) throws IOException
	{
		try//this runs when input is non-negative
		{
			int m = 1;//variable for number of months
			double isum=0;
			double psum=0;
			double t=0;
			double ratio = 0.0;
			String ratioString = "";
			if (Math.round(ratio*100)%100==0&&Math.round(ratio*100)%10!=0)
			{
				ratioString = Double.toString(ratio)+"00";
			}
			else if (Math.round(ratio*100)%10==0)
			{
				ratioString = Double.toString(ratio)+"0";
			}
			else
			{
				ratioString = Double.toString(ratio);
			}
			//Dollar instances to be printed
			Dollar zero = new Dollar (0.0);
			Dollar ins = new Dollar (monthlyInsuranceAndTax);
			Dollar pri = new Dollar (principal);
			Dollar mo = new Dollar (myMonthlyPayment);
			
			//welcome message
			System.out.println("Welcome you your personal mortgage calculator! Below is a breakdown of your mortgage plan. \n");
			System.out.format("After %3d",0);
			System.out.print(" months, you will owe " + String.format("%12s",pri.toString()) + " with " + String.format("%12s",zero.toString()) + " principal paid and " + String.format("%12s",zero.toString()) + " interest paid and " + String.format("%12s",zero.toString()) + " (insurance and tax) paid. The interest to payment ratio is "+String.format("%6s",ratioString) + "%\n");
			while(p(m,principal,rate, myMonthlyPayment, monthlyInsuranceAndTax)>0)//runs when principal is greater than or equal to 0
			{
				isum+=(r(m,principal,rate, myMonthlyPayment, monthlyInsuranceAndTax));//accumulation of all interest paid
				psum+=(myMonthlyPayment-monthlyInsuranceAndTax-r(m,principal,rate, myMonthlyPayment, monthlyInsuranceAndTax));//accumulation of all principal paid
				t = isum+psum+monthlyInsuranceAndTax*m;
				//instances to be printed
				Dollar amount = new Dollar (p(m,principal,rate, myMonthlyPayment, monthlyInsuranceAndTax));
				Dollar interest = new Dollar(r(m,principal,rate, myMonthlyPayment, monthlyInsuranceAndTax));
				Dollar principals = new Dollar (myMonthlyPayment-monthlyInsuranceAndTax-r(m,principal,rate, myMonthlyPayment, monthlyInsuranceAndTax));
				Dollar yt = new Dollar(t);
				Dollar yp = new Dollar (psum);
				Dollar yi = new Dollar (isum);
				Dollar yins = new Dollar (monthlyInsuranceAndTax*m);
				ratio = (Math.round(r(m,principal,rate, myMonthlyPayment, monthlyInsuranceAndTax)*10000/myMonthlyPayment))/100.0;
				if (Math.round(ratio*100)%100==0&&Math.round(ratio*100)%10!=0)
				{
					ratioString = Double.toString(ratio)+"00";
				}
				else if (Math.round(ratio*100)%10==0)
				{
					ratioString = Double.toString(ratio)+"0";
				}
				else
				{
					ratioString = Double.toString(ratio);
				}
				
				System.out.format("After %3d months, you will owe ",m);
				System.out.print( String.format("%12s",amount.toString()) + " with " + String.format("%12s",principals.toString()) + " principal paid and " + String.format("%12s",interest.toString()) + " interest paid and "+ String.format("%12s",ins.toString()) +" (insurance and tax) paid. The interest to payment ratio is " + String.format("%6s",ratioString) + "%\n");
				if (m%12==0)
				{
					System.out.format("\n%2d years have passed. \nYou have paid a total of ",m/12);
					System.out.print(String.format("%18s", yt.toString())+" with " + String.format("%12s", yp.toString())+ " principal paid and " + String.format("%12s", yi.toString()) + " interest paid and " + String.format("%12s", yins.toString()) + " (insurance and tax) paid \n\n");
				}
				m++;//increment number of months
			}
			double finalInterest = p(m-1,principal,rate, myMonthlyPayment, monthlyInsuranceAndTax)*rate;//calculate the interest for the last month
			double finalPrincipal = p(m-1,principal,rate, myMonthlyPayment, monthlyInsuranceAndTax);//calculate the principal for the last month
			double finalPayment = p(m-1,principal,rate, myMonthlyPayment, monthlyInsuranceAndTax)*(rate+1)+monthlyInsuranceAndTax;
			double finalRatio= (Math.round(finalInterest*10000/finalPayment))/100.0;
			if (Math.round(finalRatio*100)%100==0&&Math.round(finalRatio*100)%10!=0)
			{
				ratioString = Double.toString(finalRatio)+"00";
			}
			else if (Math.round(finalRatio*100)%10==0)
			{
				ratioString = Double.toString(finalRatio)+"0";
			}
			else
			{
				ratioString = Double.toString(finalRatio);
			}
			psum+=finalPrincipal;//add final principal to psum
			isum+=finalInterest;//add final interest to isum
			t = isum+psum+monthlyInsuranceAndTax*m;
			//instances to be printed
			Dollar fpay = new Dollar (finalPayment);
			Dollar fint = new Dollar (finalInterest);
			Dollar fprin = new Dollar (finalPrincipal);
			Dollar totalInterest = new Dollar(isum);
			Dollar totalPrincipal = new Dollar (psum);
			Dollar totalPaid = new Dollar (t);
			Dollar totalIns = new Dollar (monthlyInsuranceAndTax*m);
			System.out.format("After %3d",m); 
			System.out.print(" months, you will owe " + String.format("%12s",zero.toString()) + " with " + String.format("%12s",fprin.toString()) + " principal paid and " + String.format("%12s",fint.toString()) + " interest paid and "+ String.format("%12s",ins.toString()) +" (insurance and tax) paid. The interest to payment ratio is " + String.format("%6s",ratioString) + "%"+String.format("\n\n%8d years and %2d months have passed\n\n",m/12,m%12)+"**note: final payment may be different from monthly payment**\n");
			System.out.println("The payment for the last month is " + fpay.toString()+" and all other months (1-" + (m-1) + ") is " + mo.toString() + " per month");
			System.out.println("\nOver the course of " + String.format("%2d years and %2d month",m/12,m%12) + " you have paid a total of " + totalPaid.toString() + " which includes " + totalPrincipal.toString() + " principal and " + totalInterest.toString() + " interest and " + totalIns.toString() + " (insurance and property tax)\nNot including down payments, deposits, closing costs (origination fees, appraisal fees, title fees, prepaid insurance/interest, etc), renovations, or other fees/expenses");
		}
		catch (Exception ioe){}
	}
}
#include <iostream>

class PersonneExemple {
	private:
		std::string nom;
		int age;
		static bool test;

	public:
		std::string prenom;
		PersonneExemple(std::string , std::string );
		PersonneExemple();
		std::string toString();
		bool estMajeure();
};
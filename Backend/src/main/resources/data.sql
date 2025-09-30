-- Insertion d'une entreprise de test
INSERT INTO entreprise (id, nom, description, adresse_adresse1, adresse_ville, adresse_code_postal, adresse_pays, creation_date, last_modified_date) 
VALUES (1, 'Entreprise Test', 'Entreprise pour les tests', '123 Rue Test', 'Paris', '75001', 'France', NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- Insertion d'un utilisateur de test avec mot de passe encodé (password: "admin123")
INSERT INTO utilisateur (id, nom, prenom, email, username, mot_de_passe, enabled, account_non_expired, credentials_non_expired, account_non_locked, entreprise_id, creation_date, last_modified_date)
VALUES (1, 'Admin', 'Test', 'admin@test.com', 'admin', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', true, true, true, true, 1, NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- Insertion du rôle admin
INSERT INTO roles (id, role_name, utilisateur_id, entreprise_id, creation_date, last_modified_date)
VALUES (1, 'ADMIN', 1, 1, NOW(), NOW())
ON CONFLICT (id) DO NOTHING;